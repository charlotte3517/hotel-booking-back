package com.github.charlotte3517.hotelbooking.hotel.service.impl;

import com.github.charlotte3517.hotelbooking.googleplace.GoogleReview;
import com.github.charlotte3517.hotelbooking.dao.HotelDao;
import com.github.charlotte3517.hotelbooking.hotel.model.Hotel;
import com.github.charlotte3517.hotelbooking.hotel.data.HotelDataService;
import com.github.charlotte3517.hotelbooking.hotel.model.HotelWithReviews;
import com.github.charlotte3517.hotelbooking.hotel.service.HotelManageService;
import com.github.charlotte3517.hotelbooking.googleplace.service.GoogleMapApiService;
import com.github.charlotte3517.hotelbooking.googleplace.service.googlemap.findplace.PlaceResponse;
import com.github.charlotte3517.hotelbooking.googleplace.service.googlemap.placedetail.PlaceDetailsResponse;
import com.github.charlotte3517.hotelbooking.googleplace.service.googlemap.placedetail.Review;
import com.github.charlotte3517.hotelbooking.exception.HBException;
import com.github.charlotte3517.hotelbooking.redis.RedisKeyUtil;
import com.github.charlotte3517.hotelbooking.redis.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Service
public class HotelManageServiceImpl implements HotelManageService {

    private static final Logger logger = LoggerFactory.getLogger(HotelManageServiceImpl.class);
    private static final int HOTEL_DATA_CACHE_DAYS = 31;
    private static final int HOTEL_REVIEW_CACHE_DAYS = 32;

    @Value("${google.places.daysSinceLastReviewQuery}")
    private Integer numberOfDays;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private GoogleMapApiService googleMapApiService;

    @Autowired
    private HotelDao hotelDao;

    @Autowired
    private HotelDataService hotelDataService;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private RedisKeyUtil redisKeyUtil;

    @Override
    public List<Hotel> getAllHotels() {
        try {
            return hotelDao.getAllHotels();
        } catch (Exception e) {
            logger.error("Error occurred while fetching all hotels", e);
            throw new HBException("Failed to fetch all hotels");
        }
    }

    @Override
    public HotelWithReviews getHotelWithReviews(String hotelName) {
        try {
            Hotel hotel = getHotelByNameOrAddFromGooglePlace(hotelName);
            List<GoogleReview> reviews = getReviewsByPlaceIdOrAddFromGooglePlace(hotel.getPlaceId());
            return new HotelWithReviews(hotel, reviews);
        } catch (Exception e) {
            logger.error("Error occurred while fetching hotel and reviews for: {}", hotelName, e);
            throw new HBException("Failed to fetch hotel and reviews");
        }
    }

    @Override
    public Hotel getHotelByNameOrAddFromGooglePlace(String hotelName) {
        String redisKey = redisKeyUtil.buildHotelKey(hotelName);

        Hotel cachedHotel = redisUtil.get(redisKey, Hotel.class);
        if (cachedHotel != null) {
            logger.info("Hotel retrieved from Redis: {}", hotelName);
            return cachedHotel;
        }

        Hotel hotel = getHotelFromGooglePlace(hotelName);
        if (hotel != null) {
            redisUtil.set(redisKey, hotel, Duration.ofDays(HOTEL_DATA_CACHE_DAYS));
            logger.info("Hotel stored in Redis: {}", hotelName);
            hotelDataService.createHotelIfNotExistPlaceId(hotel);
        }

        return hotel;
    }

    private Hotel getHotelFromGooglePlace(String hotelName) {
        try {
            PlaceResponse placeResponse = googleMapApiService.findPlaceFromText(hotelName);
            Hotel hotel = generateHotel(placeResponse);

            if (!hotelName.equalsIgnoreCase(hotel.getHotelName())) {
                logger.error("Returned hotel name does not match input: {} != {}", hotelName, hotel.getHotelName());
                throw new HBException("Failed to get hotel from Google Place");
            }

            return hotel;
        } catch (Exception e) {
            logger.error("Error occurred while getting hotel from Google Place: {}", hotelName, e);
            throw new HBException("Failed to get hotel from Google Place");
        }
    }

    private Hotel generateHotel(PlaceResponse placeResponse) {
        try {
            String htmlAttribution = placeResponse.getCandidates().get(0).getPhotos().get(0).getHtml_attributions().get(0);
            String googleMapUrl = extractUrlFromHtml(htmlAttribution);

            return new Hotel()
                    .setHotelName(placeResponse.getCandidates().get(0).getName())
                    .setAddress(placeResponse.getCandidates().get(0).getFormatted_address())
                    .setGoogleMapUrl(googleMapUrl)
                    .setPlaceId(placeResponse.getCandidates().get(0).getPlace_id())
                    .setRating(placeResponse.getCandidates().get(0).getRating());
        } catch (Exception e) {
            logger.error("Error occurred while generating hotel from place response", e);
            throw new HBException("Failed to generate hotel from place response");
        }
    }

    private String extractUrlFromHtml(String htmlAttribution) {
        String regex = "href=\\\"(.*?)\\\"";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(htmlAttribution);

        if (matcher.find()) {
            return matcher.group(1);
        }

        throw new IllegalArgumentException("Invalid htmlAttribution format: " + htmlAttribution);
    }

    @Override
    public List<GoogleReview> getReviewsByPlaceIdOrAddFromGooglePlace(String placeId) {
        String redisKey = redisKeyUtil.buildGoogleReviewKey(placeId);

        List<GoogleReview> cachedReviews = redisUtil.get(redisKey, List.class);
        if (cachedReviews != null) {
            logger.debug("Google reviews retrieved from Redis for place ID: {}", placeId);
            return cachedReviews;
        }

        List<GoogleReview> googleReviews = getGoogleReviewsFromGooglePlace(placeId);
        redisUtil.set(redisKey, googleReviews, Duration.ofDays(HOTEL_REVIEW_CACHE_DAYS));
        logger.info("Google reviews stored in Redis for place ID: {}", placeId);
        return googleReviews;
    }

    private List<GoogleReview> getGoogleReviewsFromGooglePlace(String placeId) {
        try {
            PlaceDetailsResponse placeDetailsResponse = googleMapApiService.getPlaceDetails(placeId);
            return generateGoogleReviews(placeDetailsResponse, placeId);
        } catch (Exception e) {
            logger.error("Error occurred while getting Google reviews from Google Place: {}", placeId, e);
            throw new HBException("Failed to get Google reviews from Google Place");
        }
    }

    private List<GoogleReview> generateGoogleReviews(PlaceDetailsResponse placeDetailsResponse, String placeId) {
        try {
            List<Review> reviews = placeDetailsResponse.getResult().getReviews();
            List<GoogleReview> googleReviews = new ArrayList<>();

            for (Review review : reviews) {
                GoogleReview googleReview = new GoogleReview()
                        .setPlaceId(placeId)
                        .setAuthorName(review.getAuthor_name())
                        .setRating(review.getRating())
                        .setContent(review.getText())
                        .setReviewTime(review.getRelative_time_description());

                googleReviews.add(googleReview);
            }

            return googleReviews;
        } catch (Exception e) {
            logger.error("Error occurred while generating Google reviews from place details response", e);
            throw new HBException("Failed to generate Google reviews from place details response");
        }
    }
}

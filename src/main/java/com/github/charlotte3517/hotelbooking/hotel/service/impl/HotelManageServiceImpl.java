package com.github.charlotte3517.hotelbooking.hotel.service.impl;

import com.github.charlotte3517.hotelbooking.dao.RoomTypeDao;
import com.github.charlotte3517.hotelbooking.googleplace.GoogleReview;
import com.github.charlotte3517.hotelbooking.dao.GoogleReviewDao;
import com.github.charlotte3517.hotelbooking.dao.HotelDao;
import com.github.charlotte3517.hotelbooking.hotel.model.Hotel;
import com.github.charlotte3517.hotelbooking.hotel.model.RoomType;
import com.github.charlotte3517.hotelbooking.hotel.service.HotelManageService;
import com.github.charlotte3517.hotelbooking.googleplace.service.GoogleMapApiService;
import com.github.charlotte3517.hotelbooking.googleplace.service.googlemap.findplace.PlaceResponse;
import com.github.charlotte3517.hotelbooking.googleplace.service.googlemap.placedetail.PlaceDetailsResponse;
import com.github.charlotte3517.hotelbooking.googleplace.service.googlemap.placedetail.Review;
import com.github.charlotte3517.hotelbooking.exception.HBException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HotelManageServiceImpl implements HotelManageService {

    private static final Logger logger = LoggerFactory.getLogger(HotelManageServiceImpl.class);

    private static final Integer DEFAULT_USER_ID = 0;

    @Value("${google.places.daysSinceLastReviewQuery}")
    private Integer numberOfDays;

    @Autowired
    private GoogleMapApiService googleMapApiService;

    @Autowired
    private HotelDao hotelDao;

    @Autowired
    private GoogleReviewDao googleReviewDao;

    @Autowired
    private RoomTypeDao roomTypeDao;

    public List<Hotel> getAllHotels() {
        try {
            return hotelDao.getAllHotels();
        } catch (Exception e) {
            logger.error("Error occurred while fetching all hotels", e);
            throw new HBException("Failed to fetch all hotels");
        }
    }

    public Hotel getHotelById(Integer hotelId) {
        try {
            return hotelDao.getHotelById(hotelId);
        } catch (Exception e) {
            logger.error("Error occurred while fetching hotel by ID: {}", hotelId, e);
            throw new HBException("Failed to fetch hotel by ID");
        }
    }

    public Hotel getHotelByNameOrAddFromGooglePlace(String hotelName) {
        try {
            if (isExistHotelName(hotelName)) {
                return hotelDao.getHotelByName(hotelName);
            }

            Hotel hotel = getHotelFromGooglePlace(hotelName);
            createHotelIfNotExistPlaceId(hotel);
            addHotelId(hotel);
            return hotel;
        } catch (Exception e) {
            logger.error("Error occurred while fetching or adding hotel by name: {}", hotelName, e);
            throw new HBException("Failed to fetch or add hotel by name");
        }
    }

    private boolean isExistHotelName(String hotelName) {
        try {
            return hotelDao.getHotelCountByName(hotelName) > 0;
        } catch (Exception e) {
            logger.error("Error occurred while checking if hotel name exists: {}", hotelName, e);
            throw new HBException("Failed to check if hotel name exists");
        }
    }

    private Hotel getHotelFromGooglePlace(String hotelName) {
        try {
            PlaceResponse placeResponse = googleMapApiService.findPlaceFromText(hotelName);
            return generateHotel(placeResponse);
        } catch (Exception e) {
            logger.error("Error occurred while getting hotel from Google Place: {}", hotelName, e);
            throw new HBException("Failed to get hotel from Google Place");
        }
    }

    private Hotel generateHotel(PlaceResponse placeResponse) {
        try {
            return new Hotel()
                    .setHotelName(placeResponse.getCandidates().get(0).getName())
                    .setAddress(placeResponse.getCandidates().get(0).getFormatted_address())
                    .setGoogleMapUrl(placeResponse.getCandidates().get(0).getPhotos().get(0).getHtml_attributions().get(0))
                    .setPlaceId(placeResponse.getCandidates().get(0).getPlace_id())
                    .setRating(placeResponse.getCandidates().get(0).getRating());
        } catch (Exception e) {
            logger.error("Error occurred while generating hotel from place response", e);
            throw new HBException("Failed to generate hotel from place response");
        }
    }

    private void createHotelIfNotExistPlaceId(Hotel hotel) {
        try {
            if (!isExistHotelPlaceId(hotel.getPlaceId())) {
                insertHotelAndSetId(hotel);
            }
        } catch (Exception e) {
            logger.error("Error occurred while creating hotel if not exist by place ID: {}", hotel.getPlaceId(), e);
            throw new HBException("Failed to create hotel if not exist by place ID");
        }
    }

    private void addHotelId(Hotel hotel) {
        try {
            Integer hotelId = hotelDao.getHotelIdByPlaceId(hotel.getPlaceId());
            hotel.setHotelId(hotelId);
        } catch (Exception e) {
            logger.error("Error occurred while adding hotel ID: {}", hotel.getPlaceId(), e);
            throw new HBException("Failed to add hotel ID");
        }
    }

    private boolean isExistHotelPlaceId(String placeId) {
        try {
            return hotelDao.getHotelCountByPlaceId(placeId) > 0;
        } catch (Exception e) {
            logger.error("Error occurred while checking if hotel place ID exists: {}", placeId, e);
            throw new HBException("Failed to check if hotel place ID exists");
        }
    }

    private void insertHotelAndSetId(Hotel hotel) {
        try {
            hotelDao.insertHotel(hotel, DEFAULT_USER_ID);
        } catch (Exception e) {
            logger.error("Error occurred while inserting hotel and setting ID: {}", hotel.getPlaceId(), e);
            throw new HBException("Failed to insert hotel and set ID");
        }
    }

    @Override
    public List<GoogleReview> getReviewsByPlaceIdOrAddFromGooglePlace(String placeId) {
        try {
            if (isGoogleReviewExistByHotelIdInLastNDays(placeId, numberOfDays)) {
                return googleReviewDao.getGoogleReviewByPlaceIdInLastNDays(placeId, numberOfDays);
            }

            List<GoogleReview> googleReviews = getGoogleReviewsFromGooglePlace(placeId);
            createGoogleReview(googleReviews, placeId);
            return googleReviews;
        } catch (Exception e) {
            logger.error("Error occurred while fetching or adding Google reviews by place ID: {}", placeId, e);
            throw new HBException("Failed to fetch or add Google reviews by place ID");
        }
    }

    private boolean isGoogleReviewExistByHotelIdInLastNDays(String placeId, Integer numberOfDays) {
        try {
            return googleReviewDao.getGoogleReviewCountByPlaceIdInLastNDays(placeId, numberOfDays) > 0;
        } catch (Exception e) {
            logger.error("Error occurred while checking if Google reviews exist by place ID in last {} days: {}", numberOfDays, placeId, e);
            throw new HBException("Failed to check if Google reviews exist by place ID in last " + numberOfDays + " days");
        }
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

    private void createGoogleReview(List<GoogleReview> googleReviews, String placeId) {
        try {
            googleReviewDao.deleteGoogleReviewByPlaceIdBeforeLastNDays(placeId, numberOfDays);

            for (GoogleReview googleReview : googleReviews) {
                googleReviewDao.insertGoogleReview(googleReview);
            }
        } catch (Exception e) {
            logger.error("Error occurred while creating Google reviews: {}", placeId, e);
            throw new HBException("Failed to create Google reviews");
        }
    }

    public List<RoomType> getAllRoomTypes() {
        try {
            return roomTypeDao.getAllRoomTypes();
        } catch (Exception e) {
            logger.error("Error occurred while fetching all room types", e);
            throw new HBException("Failed to fetch all room types");
        }
    }
}

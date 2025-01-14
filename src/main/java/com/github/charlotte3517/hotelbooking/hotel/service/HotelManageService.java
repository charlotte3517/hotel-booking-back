package com.github.charlotte3517.hotelbooking.hotel.service;

import com.github.charlotte3517.hotelbooking.googleplace.GoogleReview;
import com.github.charlotte3517.hotelbooking.hotel.model.Hotel;
import com.github.charlotte3517.hotelbooking.hotel.model.HotelWithReviews;

import java.util.List;

public interface HotelManageService {

    List<Hotel> getAllHotels();

    HotelWithReviews getHotelWithReviews(String hotelName);

    Hotel getHotelByNameOrAddFromGooglePlace(String hotelName);

    List<GoogleReview> getReviewsByPlaceIdOrAddFromGooglePlace(String placeId);
}


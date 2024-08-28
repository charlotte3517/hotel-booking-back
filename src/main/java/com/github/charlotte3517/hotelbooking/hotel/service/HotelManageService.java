package com.github.charlotte3517.hotelbooking.hotel.service;

import com.github.charlotte3517.hotelbooking.googleplace.GoogleReview;
import com.github.charlotte3517.hotelbooking.hotel.model.Hotel;
import com.github.charlotte3517.hotelbooking.hotel.model.RoomType;

import java.util.List;

public interface HotelManageService {

    List<Hotel> getAllHotels();

    Hotel getHotelByNameOrAddFromGooglePlace(String hotelName);

    List<GoogleReview> getReviewsByPlaceIdOrAddFromGooglePlace(String placeId);

    List<RoomType> getAllRoomTypes();
}


package com.github.charlotte3517.hotelbooking.controller;

import com.github.charlotte3517.hotelbooking.dao.RoomTypeDao;
import com.github.charlotte3517.hotelbooking.hotel.model.RoomType;
import com.github.charlotte3517.hotelbooking.googleplace.GoogleReview;
import com.github.charlotte3517.hotelbooking.hotel.model.Hotel;
import com.github.charlotte3517.hotelbooking.hotel.service.HotelManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelManageService hotelManageService;

    @Autowired
    private RoomTypeDao roomTypeDao;

    @GetMapping("")
    public String eChoes() {
        return "backend connect success!";
    }

    @GetMapping("/")
    public List<Hotel> getAllHotels() {
        List<Hotel> hotels = hotelManageService.getAllHotels();
        return hotels;
    }

    @GetMapping("/status")
    public String getStatus() {
        return "Hotel Booking Service is running!";
    }

    @GetMapping("/name/{hotelName}")
    public Hotel getHotelByName(@PathVariable String hotelName) {

        Hotel hotel = hotelManageService.getHotelByNameOrAddFromGooglePlace(hotelName);
        return hotel;
    }

    @GetMapping("/reviews/{placeId}")
    public List<GoogleReview> getReviewsByPlaceId(@PathVariable String placeId) {
        return hotelManageService.getReviewsByPlaceIdOrAddFromGooglePlace(placeId);
    }

    @GetMapping("/roomtypes")
    public List<RoomType> getAllRoomTypes() {
        return hotelManageService.getAllRoomTypes();
    }
}
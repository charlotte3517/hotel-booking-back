package com.github.charlotte3517.hotelbooking.controller;

import com.github.charlotte3517.hotelbooking.hotel.model.HotelWithReviews;
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

    @GetMapping("")
    public String eChoes() {
        return "backend connect success!";
    }

    @GetMapping("/")
    public List<Hotel> getAllHotels() {
        List<Hotel> hotels = hotelManageService.getAllHotels();
        return hotels;
    }

    @GetMapping("/name/{hotelName}")
    public HotelWithReviews getHotelWithReviews(@PathVariable String hotelName) {
        return hotelManageService.getHotelWithReviews(hotelName);
    }
}
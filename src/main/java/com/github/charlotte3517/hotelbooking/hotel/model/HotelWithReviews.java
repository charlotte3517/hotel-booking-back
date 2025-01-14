package com.github.charlotte3517.hotelbooking.hotel.model;

import com.github.charlotte3517.hotelbooking.googleplace.GoogleReview;

import java.util.List;

public class HotelWithReviews {
    private Hotel hotel;
    private List<GoogleReview> reviews;

    public HotelWithReviews(Hotel hotel, List<GoogleReview> reviews) {
        this.hotel = hotel;
        this.reviews = reviews;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public List<GoogleReview> getReviews() {
        return reviews;
    }

    public void setReviews(List<GoogleReview> reviews) {
        this.reviews = reviews;
    }
}


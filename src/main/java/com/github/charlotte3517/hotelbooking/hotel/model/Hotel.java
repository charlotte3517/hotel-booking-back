package com.github.charlotte3517.hotelbooking.hotel.model;

import java.math.BigDecimal;

public class Hotel {
    private Integer hotelId;
    private String hotelName;
    private String address;
    private String googleMapUrl;
    private String placeId;
    private BigDecimal rating;

    public Integer getHotelId() {
        return hotelId;
    }

    public Hotel setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
        return this;
    }

    public String getHotelName() {
        return hotelName;
    }

    public Hotel setHotelName(String hotelName) {
        this.hotelName = hotelName;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Hotel setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getGoogleMapUrl() {
        return googleMapUrl;
    }

    public Hotel setGoogleMapUrl(String googleMapUrl) {
        this.googleMapUrl = googleMapUrl;
        return this;
    }

    public String getPlaceId() {
        return placeId;
    }

    public Hotel setPlaceId(String placeId) {
        this.placeId = placeId;
        return this;
    }

    public BigDecimal getRating() {
        return rating;
    }

    public Hotel setRating(BigDecimal rating) {
        this.rating = rating;
        return this;
    }
}
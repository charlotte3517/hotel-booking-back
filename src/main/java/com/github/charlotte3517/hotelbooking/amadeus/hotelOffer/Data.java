package com.github.charlotte3517.hotelbooking.amadeus.hotelOffer;

import java.util.List;

public class Data {
    private String type;
    private Hotel hotel;
    private boolean available;
    private List<Offer> offers;
    private String self;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public List<Offer> getOffers() {
        return offers;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }

    public String getSelf() {
        return self;
    }

    public void setSelf(String self) {
        this.self = self;
    }
}


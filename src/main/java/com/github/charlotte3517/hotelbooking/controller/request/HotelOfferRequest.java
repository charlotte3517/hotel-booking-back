package com.github.charlotte3517.hotelbooking.controller.request;

public class HotelOfferRequest {
    private String hotelIds;
    private String hotelName;
    private String checkInDate;
    private String checkOutDate;
    private int adults;
    private int roomQuantity;
    private boolean bestRateOnly = false;

    public String getHotelIds() {
        return hotelIds;
    }

    public void setHotelIds(String hotelIds) {
        this.hotelIds = hotelIds;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(String checkInDate) {
        this.checkInDate = checkInDate;
    }

    public String getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(String checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public int getAdults() {
        return adults;
    }

    public void setAdults(int adults) {
        this.adults = adults;
    }

    public int getRoomQuantity() {
        return roomQuantity;
    }

    public void setRoomQuantity(int roomQuantity) {
        this.roomQuantity = roomQuantity;
    }

    public boolean isBestRateOnly() {
        return bestRateOnly;
    }

    public void setBestRateOnly(boolean bestRateOnly) {
        this.bestRateOnly = bestRateOnly;
    }
}


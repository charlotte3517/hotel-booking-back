package com.github.charlotte3517.hotelbooking.amadeus.hotelBooking.request;

public class HotelBookingRequest {
    private Data2 data;
    private String offerId;

    public Data2 getData() {
        return data;
    }

    public void setData(Data2 data) {
        this.data = data;
    }

    public String getOfferId() {
        return offerId;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }
}

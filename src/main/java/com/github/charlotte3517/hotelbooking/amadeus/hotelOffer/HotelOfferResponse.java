package com.github.charlotte3517.hotelbooking.amadeus.hotelOffer;

import java.util.List;

public class HotelOfferResponse {
    private List<Data> data;
    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }
}


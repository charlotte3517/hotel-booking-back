package com.github.charlotte3517.hotelbooking.amadeus.hotelsByCity;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class HotelsByCityResponse {
    @JsonProperty("data")
    private List<HotelData> data;

    public List<HotelData> getData() {
        return data;
    }

    public void setData(List<HotelData> data) {
        this.data = data;
    }
}

package com.github.charlotte3517.hotelbooking.amadeus.hotelsByCity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Address {
    @JsonProperty("countryCode")
    private String countryCode;

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
}

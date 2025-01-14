package com.github.charlotte3517.hotelbooking.amadeus.hotelsByCity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Distance {
    @JsonProperty("value")
    private double value;

    @JsonProperty("unit")
    private String unit;

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}

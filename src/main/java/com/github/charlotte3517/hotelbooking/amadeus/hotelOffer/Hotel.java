package com.github.charlotte3517.hotelbooking.amadeus.hotelOffer;

public class Hotel {
    private String type;
    private String hotelId;
    private String chainCode;
    private String dupeId;
    private String name;
    private String cityCode;
    private double latitude;
    private double longitude;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public String getChainCode() {
        return chainCode;
    }

    public void setChainCode(String chainCode) {
        this.chainCode = chainCode;
    }

    public String getDupeId() {
        return dupeId;
    }

    public void setDupeId(String dupeId) {
        this.dupeId = dupeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}


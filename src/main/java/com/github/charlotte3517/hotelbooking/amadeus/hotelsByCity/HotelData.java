package com.github.charlotte3517.hotelbooking.amadeus.hotelsByCity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HotelData {
    @JsonProperty("chainCode")
    private String chainCode;

    @JsonProperty("iataCode")
    private String iataCode;

    @JsonProperty("dupeId")
    private long dupeId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("hotelId")
    private String hotelId;

    @JsonProperty("geoCode")
    private GeoCode geoCode;

    @JsonProperty("address")
    private Address address;

    @JsonProperty("distance")
    private Distance distance;

    @JsonProperty("lastUpdate")
    private String lastUpdate;

    public String getChainCode() {
        return chainCode;
    }

    public void setChainCode(String chainCode) {
        this.chainCode = chainCode;
    }

    public String getIataCode() {
        return iataCode;
    }

    public void setIataCode(String iataCode) {
        this.iataCode = iataCode;
    }

    public long getDupeId() {
        return dupeId;
    }

    public void setDupeId(long dupeId) {
        this.dupeId = dupeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public GeoCode getGeoCode() {
        return geoCode;
    }

    public void setGeoCode(GeoCode geoCode) {
        this.geoCode = geoCode;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Distance getDistance() {
        return distance;
    }

    public void setDistance(Distance distance) {
        this.distance = distance;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}

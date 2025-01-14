package com.github.charlotte3517.hotelbooking.amadeus.hotelsByCity;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class HotelsByCityResponse2 {

    @JsonProperty("data")
    private List<HotelData> data;

    public List<HotelData> getData() {
        return data;
    }

    public void setData(List<HotelData> data) {
        this.data = data;
    }

    public static class HotelData {

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

    public static class GeoCode {
        @JsonProperty("latitude")
        private double latitude;

        @JsonProperty("longitude")
        private double longitude;

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

    public static class Address {
        @JsonProperty("countryCode")
        private String countryCode;

        public String getCountryCode() {
            return countryCode;
        }

        public void setCountryCode(String countryCode) {
            this.countryCode = countryCode;
        }
    }

    public static class Distance {
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
}

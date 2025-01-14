package com.github.charlotte3517.hotelbooking.amadeus;

public enum AmadeusApiUrlPath {
    TOKEN("/v1/security/oauth2/token"),
    HOTELS_BY_CITY("/v1/reference-data/locations/hotels/by-city"),
    HOTEL_OFFERS("/v3/shopping/hotel-offers"),
    HOTEL_BOOKING("/v2/booking/hotel-orders");

    private final String path;

    AmadeusApiUrlPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}

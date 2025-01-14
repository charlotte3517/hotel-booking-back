package com.github.charlotte3517.hotelbooking.redis;

import org.springframework.stereotype.Component;

@Component
public class RedisKeyUtil {

    public String buildAmadeusTokenKey() {
        return "amadeus:accessToken";
    }

    public String buildHotelKey(String hotelName) {
        return String.format("hotel:%s", hotelName);
    }

    public String buildGoogleReviewKey(String placeId) {
        return String.format("googleReviews:%s", placeId);
    }

    public String buildHotelsByCityKey(String cityCode, int radius) {
        return String.format("hotelsByCity:%s:%d", cityCode, radius);
    }
}

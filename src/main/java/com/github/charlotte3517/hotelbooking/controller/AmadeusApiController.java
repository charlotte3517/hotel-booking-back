package com.github.charlotte3517.hotelbooking.controller;

import com.github.charlotte3517.hotelbooking.amadeus.AmadeusApiService;
import com.github.charlotte3517.hotelbooking.amadeus.hotelBooking.request.*;
import com.github.charlotte3517.hotelbooking.amadeus.hotelBooking.response.HotelBookingResponse;
import com.github.charlotte3517.hotelbooking.controller.request.HotelOfferRequest;
import com.github.charlotte3517.hotelbooking.amadeus.hotelOffer.HotelOfferResponse;
import com.github.charlotte3517.hotelbooking.amadeus.hotelsByCity.HotelsByCityResponse;
import com.github.charlotte3517.hotelbooking.amadeus.token.TokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/amadeus")
public class AmadeusApiController {
    @Autowired
    private AmadeusApiService amadeusApiService;

    @GetMapping("/token")
    public TokenResponse getToken() {
        return amadeusApiService.getToken();
    }

    @GetMapping("/hotels-by-city")
    public HotelsByCityResponse getHotelsByCity(
            @RequestParam String cityCode,
            @RequestParam int radius
    ) {
        return amadeusApiService.getHotelsByCity(cityCode, radius);
    }

    @GetMapping("/hotel-offers")
    public HotelOfferResponse getHotelOffer(HotelOfferRequest request) {
        return amadeusApiService.getHotelOffer(
                request.getHotelIds(),
                request.getHotelName(),
                request.getCheckInDate(),
                request.getCheckOutDate(),
                request.getAdults(),
                request.getRoomQuantity()
        );
    }

    @PostMapping("/book-hotel")
    public HotelBookingResponse bookHotel(@RequestBody HotelBookingRequest request) {

        HotelBookingRequest bookingRequest = amadeusApiService.createBookingRequestWithFakeData(request);
        return amadeusApiService.bookHotel(bookingRequest);
    }
}

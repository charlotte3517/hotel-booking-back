package com.github.charlotte3517.hotelbooking;

import com.github.charlotte3517.hotelbooking.amadeus.AmadeusApiService;
import com.github.charlotte3517.hotelbooking.amadeus.hotelBooking.request.*;
import com.github.charlotte3517.hotelbooking.amadeus.hotelBooking.response.HotelBookingResponse;
import com.github.charlotte3517.hotelbooking.amadeus.hotelOffer.HotelOfferResponse;
import com.github.charlotte3517.hotelbooking.amadeus.hotelsByCity.HotelsByCityResponse;
import com.github.charlotte3517.hotelbooking.amadeus.token.TokenResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class AmadeusApiIntegrationTest {

    @Autowired
    private AmadeusApiService amadeusApiService;

    @Test
    void testGetTokenIntegration() {
        TokenResponse response = amadeusApiService.getToken();

        assertNotNull(response);
        assertNotNull(response.getAccessToken());
    }

    @Test
    void testGetHotelsByCity() {
        HotelsByCityResponse response = amadeusApiService.getHotelsByCity("TNN", 10);

        assertNotNull(response, "Response should not be null");
        assertNotNull(response.getData(), "Response data should not be null");
        assertFalse(response.getData().isEmpty(), "Response data should not be empty");

        response.getData().forEach(hotel -> {
            assertNotNull(hotel.getName(), "Hotel name should not be null");
            assertNotNull(hotel.getHotelId(), "Hotel ID should not be null");
            assertNotNull(hotel.getDistance(), "Hotel distance should not be null");
            assertNotNull(hotel.getDistance().getValue(), "Distance value should not be null");
            assertNotNull(hotel.getDistance().getUnit(), "Distance unit should not be null");
        });
    }

    @Test
    void testGetHotelOffer() {
        String hotelIds = "MCLONGHM";
        String hotelName = "JW Marriott Grosvenor House London";
        int adults = 2;
        int roomQuantity = 1;

        LocalDate today = LocalDate.now();
        String checkInDate = today.plusDays(1).format(DateTimeFormatter.ISO_LOCAL_DATE);
        String checkOutDate = today.plusDays(2).format(DateTimeFormatter.ISO_LOCAL_DATE);

        HotelOfferResponse response = amadeusApiService.getHotelOffer(
                hotelIds, hotelName, checkInDate, checkOutDate, adults, roomQuantity
        );

        assertNotNull(response);
        assertFalse(response.getData().isEmpty());

        response.getData().forEach(data -> {
            System.out.println("Hotel Name: " + data.getHotel().getName());
            System.out.println("Offer ID: " + data.getOffers().get(0).getId());
            System.out.println("Total Price: " + data.getOffers().get(0).getPrice().getTotal() + " " + data.getOffers().get(0).getPrice().getCurrency());
        });
    }

    @Test
    void testBookHotel() {
        String hotelIds = "MCLONGHM";
        String hotelName = "JW Marriott Grosvenor House London";
        int adults = 2;
        int roomQuantity = 1;

        LocalDate today = LocalDate.now();
        String checkInDate = today.plusDays(1).format(DateTimeFormatter.ISO_LOCAL_DATE);
        String checkOutDate = today.plusDays(2).format(DateTimeFormatter.ISO_LOCAL_DATE);

        HotelOfferResponse hotelOfferResponse = amadeusApiService.getHotelOffer(
                hotelIds, hotelName, checkInDate, checkOutDate, adults, roomQuantity
        );

        assertNotNull(hotelOfferResponse);
        assertFalse(hotelOfferResponse.getData().isEmpty());

        String hotelOfferId = hotelOfferResponse.getData().get(0).getOffers().get(0).getId();
        assertNotNull(hotelOfferId);
        System.out.println("Hotel Offer ID: " + hotelOfferId);

        HotelBookingRequest bookingRequest = new HotelBookingRequest();
        Data2 data = new Data2();
        data.setType("hotel-order");

        Guest guest = new Guest();
        guest.setTid(1);
        guest.setTitle("MR");
        guest.setFirstName("BOB");
        guest.setLastName("SMITH");
        guest.setPhone("+33679278416");
        guest.setEmail("bob.smith@email.com");
        data.setGuests(List.of(guest));

        TravelAgent travelAgent = new TravelAgent();
        Contact contact = new Contact();
        contact.setEmail("bob.smith@email.com");
        travelAgent.setContact(contact);
        data.setTravelAgent(travelAgent);

        RoomAssociation roomAssociation = new RoomAssociation();
        GuestReference guestReference = new GuestReference();
        guestReference.setGuestReference("1");
        roomAssociation.setGuestReferences(List.of(guestReference));
        roomAssociation.setHotelOfferId(hotelOfferId);
        data.setRoomAssociations(List.of(roomAssociation));

        Payment payment = new Payment();
        payment.setMethod("CREDIT_CARD");

        PaymentCard paymentCard = new PaymentCard();
        PaymentCardInfo paymentCardInfo = new PaymentCardInfo();
        paymentCardInfo.setVendorCode("VI");
        paymentCardInfo.setCardNumber("4151289722471370");
        paymentCardInfo.setExpiryDate("2026-08");
        paymentCardInfo.setHolderName("BOB SMITH");
        paymentCard.setPaymentCardInfo(paymentCardInfo);
        payment.setPaymentCard(paymentCard);
        data.setPayment(payment);

        bookingRequest.setData(data);

        HotelBookingResponse response = amadeusApiService.bookHotel(bookingRequest);

        assertNotNull(response);
        assertNotNull(response.getData());
        System.out.println("Booking ID: " + response.getData().getId());
    }
}

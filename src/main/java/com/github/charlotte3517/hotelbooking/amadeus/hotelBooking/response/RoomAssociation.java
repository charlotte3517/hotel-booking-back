package com.github.charlotte3517.hotelbooking.amadeus.hotelBooking.response;

import java.util.List;

public class RoomAssociation {
    private String hotelOfferId;
    private List<GuestReference> guestReferences;

    public String getHotelOfferId() {
        return hotelOfferId;
    }

    public void setHotelOfferId(String hotelOfferId) {
        this.hotelOfferId = hotelOfferId;
    }

    public List<GuestReference> getGuestReferences() {
        return guestReferences;
    }

    public void setGuestReferences(List<GuestReference> guestReferences) {
        this.guestReferences = guestReferences;
    }
}

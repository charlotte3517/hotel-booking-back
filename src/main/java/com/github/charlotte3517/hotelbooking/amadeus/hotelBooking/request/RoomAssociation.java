package com.github.charlotte3517.hotelbooking.amadeus.hotelBooking.request;

import java.util.List;

public class RoomAssociation {
    private List<GuestReference> guestReferences;
    private String hotelOfferId;

    public List<GuestReference> getGuestReferences() {
        return guestReferences;
    }

    public void setGuestReferences(List<GuestReference> guestReferences) {
        this.guestReferences = guestReferences;
    }

    public String getHotelOfferId() {
        return hotelOfferId;
    }

    public void setHotelOfferId(String hotelOfferId) {
        this.hotelOfferId = hotelOfferId;
    }
}

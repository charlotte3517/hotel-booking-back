package com.github.charlotte3517.hotelbooking.amadeus.hotelBooking.response;

import java.util.List;

public class Data {
    private String type;
    private String id;
    private List<HotelBooking> hotelBookings;
    private List<Guest> guests;
    private List<AssociatedRecord> associatedRecords;
    private String self;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<HotelBooking> getHotelBookings() {
        return hotelBookings;
    }

    public void setHotelBookings(List<HotelBooking> hotelBookings) {
        this.hotelBookings = hotelBookings;
    }

    public List<Guest> getGuests() {
        return guests;
    }

    public void setGuests(List<Guest> guests) {
        this.guests = guests;
    }

    public List<AssociatedRecord> getAssociatedRecords() {
        return associatedRecords;
    }

    public void setAssociatedRecords(List<AssociatedRecord> associatedRecords) {
        this.associatedRecords = associatedRecords;
    }

    public String getSelf() {
        return self;
    }

    public void setSelf(String self) {
        this.self = self;
    }
}

package com.github.charlotte3517.hotelbooking.amadeus.hotelBooking.request;

import java.util.List;

public class Data2 {
    private String type;
    private List<Guest> guests;
    private TravelAgent travelAgent;
    private List<RoomAssociation> roomAssociations;
    private Payment payment;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Guest> getGuests() {
        return guests;
    }

    public void setGuests(List<Guest> guests) {
        this.guests = guests;
    }

    public TravelAgent getTravelAgent() {
        return travelAgent;
    }

    public void setTravelAgent(TravelAgent travelAgent) {
        this.travelAgent = travelAgent;
    }

    public List<RoomAssociation> getRoomAssociations() {
        return roomAssociations;
    }

    public void setRoomAssociations(List<RoomAssociation> roomAssociations) {
        this.roomAssociations = roomAssociations;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}

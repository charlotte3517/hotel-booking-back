package com.github.charlotte3517.hotelbooking.amadeus.hotelBooking.response;

import java.util.List;

public class HotelBooking {
    private String type;
    private String id;
    private String bookingStatus;
    private List<HotelProviderInformation> hotelProviderInformation;
    private List<RoomAssociation> roomAssociations;
    private HotelOffer hotelOffer;
    private Hotel hotel;
    private Payment payment;
    private String travelAgentId;

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

    public String getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public List<HotelProviderInformation> getHotelProviderInformation() {
        return hotelProviderInformation;
    }

    public void setHotelProviderInformation(List<HotelProviderInformation> hotelProviderInformation) {
        this.hotelProviderInformation = hotelProviderInformation;
    }

    public List<RoomAssociation> getRoomAssociations() {
        return roomAssociations;
    }

    public void setRoomAssociations(List<RoomAssociation> roomAssociations) {
        this.roomAssociations = roomAssociations;
    }

    public HotelOffer getHotelOffer() {
        return hotelOffer;
    }

    public void setHotelOffer(HotelOffer hotelOffer) {
        this.hotelOffer = hotelOffer;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public String getTravelAgentId() {
        return travelAgentId;
    }

    public void setTravelAgentId(String travelAgentId) {
        this.travelAgentId = travelAgentId;
    }
}

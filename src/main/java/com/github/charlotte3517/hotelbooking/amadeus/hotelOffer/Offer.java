package com.github.charlotte3517.hotelbooking.amadeus.hotelOffer;

public class Offer {
    private String id;
    private int roomQuantity;
    private String checkInDate;
    private String checkOutDate;
    private String rateCode;
    private RateFamilyEstimated rateFamilyEstimated;
    private Room room;
    private Guests guests;
    private Price price;
    private Policies policies;
    private String self;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getRoomQuantity() {
        return roomQuantity;
    }

    public void setRoomQuantity(int roomQuantity) {
        this.roomQuantity = roomQuantity;
    }

    public String getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(String checkInDate) {
        this.checkInDate = checkInDate;
    }

    public String getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(String checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public String getRateCode() {
        return rateCode;
    }

    public void setRateCode(String rateCode) {
        this.rateCode = rateCode;
    }

    public RateFamilyEstimated getRateFamilyEstimated() {
        return rateFamilyEstimated;
    }

    public void setRateFamilyEstimated(RateFamilyEstimated rateFamilyEstimated) {
        this.rateFamilyEstimated = rateFamilyEstimated;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Guests getGuests() {
        return guests;
    }

    public void setGuests(Guests guests) {
        this.guests = guests;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public Policies getPolicies() {
        return policies;
    }

    public void setPolicies(Policies policies) {
        this.policies = policies;
    }

    public String getSelf() {
        return self;
    }

    public void setSelf(String self) {
        this.self = self;
    }
}


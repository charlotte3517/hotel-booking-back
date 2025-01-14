package com.github.charlotte3517.hotelbooking.amadeus.hotelBooking.response;

public class HotelOffer {
    private String id;
    private String type;
    private String category;
    private String checkInDate;
    private String checkOutDate;
    private Guests guests;
    private Policies policies;
    private Price price;
    private String rateCode;
    private Room room;
    private int roomQuantity;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public Guests getGuests() {
        return guests;
    }

    public void setGuests(Guests guests) {
        this.guests = guests;
    }

    public Policies getPolicies() {
        return policies;
    }

    public void setPolicies(Policies policies) {
        this.policies = policies;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public String getRateCode() {
        return rateCode;
    }

    public void setRateCode(String rateCode) {
        this.rateCode = rateCode;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public int getRoomQuantity() {
        return roomQuantity;
    }

    public void setRoomQuantity(int roomQuantity) {
        this.roomQuantity = roomQuantity;
    }
}

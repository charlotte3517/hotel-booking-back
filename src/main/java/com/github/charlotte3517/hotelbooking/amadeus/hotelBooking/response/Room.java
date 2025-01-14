package com.github.charlotte3517.hotelbooking.amadeus.hotelBooking.response;

public class Room {
    private RoomDescription description;
    private String type;

    public RoomDescription getDescription() {
        return description;
    }

    public void setDescription(RoomDescription description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

package com.github.charlotte3517.hotelbooking.model;

import java.util.Date;

public class DailyRoomAvailability {
    private Integer roomAvailabilityId;
    private Integer hotelId;
    private Integer roomTypeId;
    private Date stayDate;
    private Integer availableRooms;
    private Integer soldRooms;
    private Integer bookableRooms;

    public Integer getRoomAvailabilityId() {
        return roomAvailabilityId;
    }

    public void setRoomAvailabilityId(Integer roomAvailabilityId) {
        this.roomAvailabilityId = roomAvailabilityId;
    }

    public Integer getHotelId() {
        return hotelId;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public Integer getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(Integer roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public Date getStayDate() {
        return stayDate;
    }

    public void setStayDate(Date stayDate) {
        this.stayDate = stayDate;
    }

    public Integer getAvailableRooms() {
        return availableRooms;
    }

    public void setAvailableRooms(Integer availableRooms) {
        this.availableRooms = availableRooms;
    }

    public Integer getSoldRooms() {
        return soldRooms;
    }

    public void setSoldRooms(Integer soldRooms) {
        this.soldRooms = soldRooms;
    }

    public Integer getBookableRooms() {
        return bookableRooms;
    }

    public void setBookableRooms(Integer bookableRooms) {
        this.bookableRooms = bookableRooms;
    }
}

package com.github.charlotte3517.hotelbooking.hotel.model;

import java.math.BigDecimal;

public class RoomType {
    private Integer roomTypeId;
    private Integer hotelId;
    private String roomName;
    private Integer minPeople;
    private Integer maxPeople;
    private Integer bedTypeId;
    private BigDecimal normalDayPrice;
    private BigDecimal holidayPrice;
    private Integer roomCapacity;
    private boolean privateBath;
    private boolean wifi;
    private boolean breakfast;
    private boolean miniBar;
    private boolean roomService;
    private boolean television;
    private boolean airConditioner;
    private boolean refrigerator;
    private boolean smokeFree;
    private boolean childFriendly;
    private boolean petFriendly;

    public Integer getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(Integer roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public Integer getHotelId() {
        return hotelId;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Integer getMinPeople() {
        return minPeople;
    }

    public void setMinPeople(Integer minPeople) {
        this.minPeople = minPeople;
    }

    public Integer getMaxPeople() {
        return maxPeople;
    }

    public void setMaxPeople(Integer maxPeople) {
        this.maxPeople = maxPeople;
    }

    public Integer getBedTypeId() {
        return bedTypeId;
    }

    public void setBedTypeId(Integer bedTypeId) {
        this.bedTypeId = bedTypeId;
    }

    public BigDecimal getNormalDayPrice() {
        return normalDayPrice;
    }

    public void setNormalDayPrice(BigDecimal normalDayPrice) {
        this.normalDayPrice = normalDayPrice;
    }

    public BigDecimal getHolidayPrice() {
        return holidayPrice;
    }

    public void setHolidayPrice(BigDecimal holidayPrice) {
        this.holidayPrice = holidayPrice;
    }

    public Integer getRoomCapacity() {
        return roomCapacity;
    }

    public void setRoomCapacity(Integer roomCapacity) {
        this.roomCapacity = roomCapacity;
    }

    public boolean isPrivateBath() {
        return privateBath;
    }

    public void setPrivateBath(boolean privateBath) {
        this.privateBath = privateBath;
    }

    public boolean isWifi() {
        return wifi;
    }

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }

    public boolean isBreakfast() {
        return breakfast;
    }

    public void setBreakfast(boolean breakfast) {
        this.breakfast = breakfast;
    }

    public boolean isMiniBar() {
        return miniBar;
    }

    public void setMiniBar(boolean miniBar) {
        this.miniBar = miniBar;
    }

    public boolean isRoomService() {
        return roomService;
    }

    public void setRoomService(boolean roomService) {
        this.roomService = roomService;
    }

    public boolean isTelevision() {
        return television;
    }

    public void setTelevision(boolean television) {
        this.television = television;
    }

    public boolean isAirConditioner() {
        return airConditioner;
    }

    public void setAirConditioner(boolean airConditioner) {
        this.airConditioner = airConditioner;
    }

    public boolean isRefrigerator() {
        return refrigerator;
    }

    public void setRefrigerator(boolean refrigerator) {
        this.refrigerator = refrigerator;
    }

    public boolean isSmokeFree() {
        return smokeFree;
    }

    public void setSmokeFree(boolean smokeFree) {
        this.smokeFree = smokeFree;
    }

    public boolean isChildFriendly() {
        return childFriendly;
    }

    public void setChildFriendly(boolean childFriendly) {
        this.childFriendly = childFriendly;
    }

    public boolean isPetFriendly() {
        return petFriendly;
    }

    public void setPetFriendly(boolean petFriendly) {
        this.petFriendly = petFriendly;
    }
}
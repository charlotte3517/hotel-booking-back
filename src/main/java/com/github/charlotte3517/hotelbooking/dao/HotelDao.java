package com.github.charlotte3517.hotelbooking.dao;

import com.github.charlotte3517.hotelbooking.hotel.model.Hotel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface HotelDao {

    List<Hotel> getAllHotels();

    Hotel getHotelById(@Param("hotelId") Integer hotelId);

    Hotel getHotelByName(@Param("hotelName") String hotelName);

    void insertHotel(@Param("hotel") Hotel hotel, @Param("userId") Integer userId);

    Integer getHotelCountByPlaceId(@Param("placeId") String placeId);
}
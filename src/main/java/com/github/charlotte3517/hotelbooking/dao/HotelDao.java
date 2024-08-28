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

    Integer getHotelIdByPlaceId(@Param("placeId") String placeId);

    void insertHotel(@Param("hotel") Hotel hotel, @Param("userId") Integer userId);

    void updateHotel(@Param("hotel") Hotel hotel, @Param("userId") Integer userId);

    void deleteHotel(@Param("hotelId") Integer hotelId);

    Integer getHotelCountByName(@Param("hotelName") String hotelName);

    Integer getHotelCountByPlaceId(@Param("placeId") String placeId);
}
package com.github.charlotte3517.hotelbooking.dao;

import com.github.charlotte3517.hotelbooking.hotel.model.RoomType;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RoomTypeDao {

    List<RoomType> getAllRoomTypes();

    RoomType getRoomTypeById(@Param("roomTypeId") Integer roomTypeId);

    void insertRoomType(@Param("roomType") RoomType roomType);

    void updateRoomType(@Param("roomType") RoomType roomType);

    void deleteRoomType(@Param("roomTypeId") Integer roomTypeId);
}
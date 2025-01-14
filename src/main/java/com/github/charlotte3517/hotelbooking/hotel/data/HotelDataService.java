package com.github.charlotte3517.hotelbooking.hotel.data;

import com.github.charlotte3517.hotelbooking.amadeus.AmadeusApiService;
import com.github.charlotte3517.hotelbooking.dao.HotelDao;
import com.github.charlotte3517.hotelbooking.exception.HBException;
import com.github.charlotte3517.hotelbooking.hotel.model.Hotel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class HotelDataService {

    private static final Logger logger = LoggerFactory.getLogger(HotelDataService.class);
    private static final Integer DEFAULT_USER_ID = 0;

    @Autowired
    private HotelDao hotelDao;

    public Hotel getHotelById(Integer hotelId) {
        try {
            return hotelDao.getHotelById(hotelId);
        } catch (Exception e) {
            logger.error("Error occurred while fetching hotel by ID: {}", hotelId, e);
            throw new HBException("Failed to fetch hotel by ID");
        }
    }

    public void createHotelIfNotExistPlaceId(Hotel hotel) {
        try {
            if (!isExistHotelPlaceId(hotel.getPlaceId())) {
                insertHotelAndSetId(hotel);
            }
        } catch (Exception e) {
            logger.error("Error occurred while creating hotel if not exist by place ID: {}", hotel.getPlaceId(), e);
            throw new HBException("Failed to create hotel if not exist by place ID");
        }
    }

    private boolean isExistHotelPlaceId(String placeId) {
        try {
            return hotelDao.getHotelCountByPlaceId(placeId) > 0;
        } catch (Exception e) {
            logger.error("Error occurred while checking if hotel place ID exists: {}", placeId, e);
            throw new HBException("Failed to check if hotel place ID exists");
        }
    }

    private void insertHotelAndSetId(Hotel hotel) {
        try {
            hotelDao.insertHotel(hotel, DEFAULT_USER_ID);
        } catch (Exception e) {
            logger.error("Error occurred while inserting hotel and setting ID: {}", hotel.getPlaceId(), e);
            throw new HBException("Failed to insert hotel and set ID");
        }
    }
}
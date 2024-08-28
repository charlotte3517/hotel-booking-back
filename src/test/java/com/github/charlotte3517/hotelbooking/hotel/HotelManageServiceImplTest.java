package com.github.charlotte3517.hotelbooking.hotel;

import com.github.charlotte3517.hotelbooking.dao.GoogleReviewDao;
import com.github.charlotte3517.hotelbooking.dao.HotelDao;
import com.github.charlotte3517.hotelbooking.dao.RoomTypeDao;
import com.github.charlotte3517.hotelbooking.googleplace.GoogleReview;
import com.github.charlotte3517.hotelbooking.googleplace.service.GoogleMapApiService;
import com.github.charlotte3517.hotelbooking.googleplace.service.googlemap.findplace.PlaceResponse;
import com.github.charlotte3517.hotelbooking.googleplace.service.googlemap.placedetail.PlaceDetailsResponse;
import com.github.charlotte3517.hotelbooking.hotel.model.Hotel;
import com.github.charlotte3517.hotelbooking.hotel.model.RoomType;
import com.github.charlotte3517.hotelbooking.hotel.service.impl.HotelManageServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class HotelManageServiceImplTest {

    @Mock
    private GoogleMapApiService googleMapApiService;

    @Mock
    private HotelDao hotelDao;

    @Mock
    private GoogleReviewDao googleReviewDao;

    @Mock
    private RoomTypeDao roomTypeDao;

    @InjectMocks
    private HotelManageServiceImpl hotelManageService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllHotels() {
        List<Hotel> hotels = Arrays.asList(new Hotel(), new Hotel());
        when(hotelDao.getAllHotels()).thenReturn(hotels);

        List<Hotel> result = hotelManageService.getAllHotels();

        assertEquals(2, result.size());
        verify(hotelDao, times(1)).getAllHotels();
    }

    @Test
    public void testGetHotelById() {
        Hotel hotel = new Hotel();
        when(hotelDao.getHotelById(1)).thenReturn(hotel);

        Hotel result = hotelManageService.getHotelById(1);

        assertNotNull(result);
        verify(hotelDao, times(1)).getHotelById(1);
    }

    @Test
    public void testGetHotelByNameOrAddFromGooglePlace_HotelExists() {
        Hotel hotel = new Hotel();
        when(hotelDao.getHotelCountByName("Test Hotel")).thenReturn(1);
        when(hotelDao.getHotelByName("Test Hotel")).thenReturn(hotel);

        Hotel result = hotelManageService.getHotelByNameOrAddFromGooglePlace("Test Hotel");

        assertNotNull(result);
        verify(hotelDao, times(1)).getHotelCountByName("Test Hotel");
        verify(hotelDao, times(1)).getHotelByName("Test Hotel");
    }

    @Test
    public void testGetHotelByNameOrAddFromGooglePlace_HotelNotExists() {
        Hotel hotel = new Hotel().setPlaceId("123").setHotelName("Test Hotel");
        when(hotelDao.getHotelCountByName("Test Hotel")).thenReturn(0);
        when(googleMapApiService.findPlaceFromText("Test Hotel")).thenReturn(new PlaceResponse());
        when(hotelDao.getHotelIdByPlaceId("123")).thenReturn(1);

        Hotel result = hotelManageService.getHotelByNameOrAddFromGooglePlace("Test Hotel");

        assertNotNull(result);
        verify(hotelDao, times(1)).getHotelCountByName("Test Hotel");
        verify(googleMapApiService, times(1)).findPlaceFromText("Test Hotel");
        verify(hotelDao, times(1)).getHotelIdByPlaceId("123");
    }

    @Test
    public void testGetReviewsByPlaceIdOrAddFromGooglePlace_ReviewsExist() {
        List<GoogleReview> reviews = Arrays.asList(new GoogleReview(), new GoogleReview());
        when(googleReviewDao.getGoogleReviewCountByPlaceIdInLastNDays("123", 30)).thenReturn(2);
        when(googleReviewDao.getGoogleReviewByPlaceIdInLastNDays("123", 30)).thenReturn(reviews);

        List<GoogleReview> result = hotelManageService.getReviewsByPlaceIdOrAddFromGooglePlace("123");

        assertEquals(2, result.size());
        verify(googleReviewDao, times(1)).getGoogleReviewCountByPlaceIdInLastNDays("123", 30);
        verify(googleReviewDao, times(1)).getGoogleReviewByPlaceIdInLastNDays("123", 30);
    }

    @Test
    public void testGetReviewsByPlaceIdOrAddFromGooglePlace_ReviewsNotExist() {
        when(googleReviewDao.getGoogleReviewCountByPlaceIdInLastNDays("123", 30)).thenReturn(0);
        when(googleMapApiService.getPlaceDetails("123")).thenReturn(new PlaceDetailsResponse());

        List<GoogleReview> result = hotelManageService.getReviewsByPlaceIdOrAddFromGooglePlace("123");

        assertNotNull(result);
        verify(googleReviewDao, times(1)).getGoogleReviewCountByPlaceIdInLastNDays("123", 30);
        verify(googleMapApiService, times(1)).getPlaceDetails("123");
    }

    @Test
    public void testGetAllRoomTypes() {
        List<RoomType> roomTypes = Arrays.asList(new RoomType(), new RoomType());
        when(roomTypeDao.getAllRoomTypes()).thenReturn(roomTypes);

        List<RoomType> result = hotelManageService.getAllRoomTypes();

        assertEquals(2, result.size());
        verify(roomTypeDao, times(1)).getAllRoomTypes();
    }
}


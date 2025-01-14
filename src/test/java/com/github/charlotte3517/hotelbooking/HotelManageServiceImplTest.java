package com.github.charlotte3517.hotelbooking;

import com.github.charlotte3517.hotelbooking.dao.HotelDao;
import com.github.charlotte3517.hotelbooking.googleplace.service.GoogleMapApiService;
import com.github.charlotte3517.hotelbooking.hotel.model.Hotel;
import com.github.charlotte3517.hotelbooking.hotel.service.impl.HotelManageServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

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
}


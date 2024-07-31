package com.hotel.HotelService.Services;

import com.hotel.HotelService.entities.Hotel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HotelService {

    Hotel craeteHotel(Hotel hotel);
    List<Hotel> getAllHotel();

    Hotel getHotelById(String hotelId);
}

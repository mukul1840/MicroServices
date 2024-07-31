package com.hotel.HotelService.Services;

import com.hotel.HotelService.entities.Hotel;
import com.hotel.HotelService.exception.ResourceNotFoundException;
import com.hotel.HotelService.repositories.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService{

    @Autowired
    private HotelRepository hotelRepository;
    @Override
    public Hotel craeteHotel(Hotel hotel) {
        String randomhotelId= UUID.randomUUID().toString();
        hotel.setHotelId(randomhotelId);
        return hotelRepository.save(hotel);

    }

    @Override
    public List<Hotel> getAllHotel() {
       List<Hotel> hotelList = hotelRepository.findAll();
       return hotelList;
    }

    @Override
    public Hotel getHotelById(String hotelId) {
        Hotel hotel = hotelRepository.findById(hotelId).orElseThrow(()-> new ResourceNotFoundException("Hotel Not Found"));
        return  hotel;
    }
}

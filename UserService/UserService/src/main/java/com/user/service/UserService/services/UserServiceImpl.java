package com.user.service.UserService.services;

import com.user.service.UserService.entities.Hotel;
import com.user.service.UserService.entities.Rating;
import com.user.service.UserService.entities.User;
import com.user.service.UserService.exception.ResourceNotFoundException;
import com.user.service.UserService.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserServiceImpl implements  UserService{
    @Autowired
    private UserRepository userRepository ;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public User saveUser(User user) {
      String randomUserId=  UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {

        List<User> userList= userRepository.findAll();
          List<User> updatedUserList= userList.stream().map((user)->{
               Rating[] ratings=restTemplate.getForObject("http://RATINGSERVICE/ratings/users/"+user.getUserId(), Rating[].class);
               user.setRatings(Arrays.asList(ratings));
               List<Rating> ratingList= Stream.of(ratings).map((rating)->{
                   ResponseEntity<Hotel> hotelResponseEntity= restTemplate.getForEntity("http://HOTELSERVICE/hotels/"+rating.getHotelId(),Hotel.class);
                   Hotel hotel= hotelResponseEntity.getBody();
                   rating.setHotel(hotel);
                   return rating;
               }).collect(Collectors.toList());
               return user;
           }).collect(Collectors.toList());

        return updatedUserList;
    }

    @Override
    public User getUserById(String userId) {
        User user= userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User Not Found"));

        //localhost:8083/ratings/users/c66acdb0-ba4a-4d22-a298-96ff9d890ddd

      Rating[] ratings=restTemplate.getForObject("http://RATINGSERVICE/ratings/users/"+user.getUserId(), Rating[].class);
      user.setRatings(Arrays.asList(ratings));

       List<Rating> ratingList= Stream.of(ratings).map((rating)->{
           ResponseEntity<Hotel> hotelResponseEntity= restTemplate.getForEntity("http://HOTELSERVICE/hotels/"+rating.getHotelId(),Hotel.class);
        Hotel hotel= hotelResponseEntity.getBody();
        rating.setHotel(hotel);
        return rating;
        }).collect(Collectors.toList());
        return user;
    }
}

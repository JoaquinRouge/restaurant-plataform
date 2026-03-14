package com.joaquinrouge.restaurant.service;

import com.joaquinrouge.restaurant.dto.CreateOrUpdateRestaurantDto;
import com.joaquinrouge.restaurant.enums.RestaurantStatus;
import com.joaquinrouge.restaurant.exception.ForbiddenException;
import com.joaquinrouge.restaurant.exception.InvalidRestaurantDataException;
import com.joaquinrouge.restaurant.exception.RestaurantNotFoundException;
import com.joaquinrouge.restaurant.model.Restaurant;
import com.joaquinrouge.restaurant.repository.IRestaurantRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RestaurantService implements IRestaurantService{

    private final IRestaurantRepository restaurantRepository;

    public RestaurantService(IRestaurantRepository restaurantRepository){
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public Restaurant findById(Long id) {
        return restaurantRepository.findById(id).orElseThrow(
                ()-> new RestaurantNotFoundException("Restaurant not found for id " + id)
        );
    }

    @Override
    public List<Restaurant> findByUserId(Long id){
        return restaurantRepository.findByUserId(id).orElseThrow(
                ()-> new RestaurantNotFoundException("Restaurant not found for id " + id)
        );
    }

    @Override
    public Restaurant createRestaurant(CreateOrUpdateRestaurantDto data) {

        validate(data);

        Restaurant restaurant = new Restaurant();

        //TODO - Setear id de usuario al restaurante
        restaurant.setUserId(1L);
        restaurant.setName(data.name());
        restaurant.setAddress(data.address());
        restaurant.setCreatedAt(LocalDateTime.now());
        restaurant.setStatus(RestaurantStatus.SUSPENDED);

        return restaurantRepository.save(restaurant);

    }

    @Override
    public void updateRestaurant(Long userId,Long restaurantId, CreateOrUpdateRestaurantDto data) {

        validate(data);

        Restaurant restaurant = findById(restaurantId);

        restaurantBelongsToUser(restaurant,userId);

        restaurant.setName(data.name());
        restaurant.setAddress(data.address());

        restaurantRepository.save(restaurant);

    }

    @Override
    public void disableRestaurant(Long userId,Long restaurantId) {

        Restaurant restaurant = findById(restaurantId);

        restaurantBelongsToUser(restaurant,userId);

        restaurant.setStatus(RestaurantStatus.SUSPENDED);

    }

    @Override
    public void enableRestaurant(Long userId,Long restaurantId){

        Restaurant restaurant = findById(restaurantId);

        restaurantBelongsToUser(restaurant,userId);

        restaurant.setStatus(RestaurantStatus.ACTIVE);
    }

    private void validate(CreateOrUpdateRestaurantDto data){
        if(data.name().isEmpty() || data.name().isBlank()){
            throw new InvalidRestaurantDataException("Invalid name");
        }

        if(data.address().isEmpty() || data.address().isBlank()){
            throw new InvalidRestaurantDataException("Invalid address");
        }
    }

    private void restaurantBelongsToUser(Restaurant restaurant,Long userId){
        if(restaurant.getUserId().equals(userId)){
            throw new ForbiddenException("You are not allowed to modify this restaurant");
        }
    }

}

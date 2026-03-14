package com.joaquinrouge.restaurant.service;

import com.joaquinrouge.restaurant.dto.CreateOrUpdateRestaurantDto;
import com.joaquinrouge.restaurant.model.Restaurant;

import java.util.List;
import java.util.Optional;

public interface IRestaurantService {

    Restaurant findById(Long id);
    List<Restaurant> findByUserId(Long id);
    Restaurant createRestaurant(CreateOrUpdateRestaurantDto data);
    void updateRestaurant(Long userId,Long restaurantId,CreateOrUpdateRestaurantDto data);
    void disableRestaurant(Long userId,Long restaurantId);
    void enableRestaurant(Long userId,Long restaurantId);
}

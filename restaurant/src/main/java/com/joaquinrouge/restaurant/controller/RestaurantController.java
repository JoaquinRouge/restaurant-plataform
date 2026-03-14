package com.joaquinrouge.restaurant.controller;

import com.joaquinrouge.restaurant.dto.CreateOrUpdateRestaurantDto;
import com.joaquinrouge.restaurant.model.Restaurant;
import com.joaquinrouge.restaurant.service.IRestaurantService;
import com.joaquinrouge.restaurant.service.RestaurantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    private final IRestaurantService restaurantService;

    public RestaurantController(IRestaurantService restaurantService){
        this.restaurantService = restaurantService;
    }

    @GetMapping("/user")
    public ResponseEntity<List<Restaurant>> findByUserId(){
        //TODO - Extraer el id del token recibido
        Long userId = 1L;

        return ResponseEntity.ok().body(restaurantService.findByUserId(userId));
    }

    @PostMapping()
    public ResponseEntity<Restaurant> createRestaurant(@RequestBody CreateOrUpdateRestaurantDto data){
        return ResponseEntity.status(HttpStatus.CREATED).body(restaurantService.createRestaurant(data));
    }

    @PutMapping("/update/{restaurantId}")
    public ResponseEntity<Void> updateRestaurant(@PathVariable Long restaurantId,@RequestBody CreateOrUpdateRestaurantDto data){
        //TODO - Extraer el id del token recibido
        Long userId = 1L;

        restaurantService.updateRestaurant(userId,restaurantId,data);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/disable/{restaurantId}")
    public ResponseEntity<Void> disableRestaurant(@PathVariable Long restaurantId){
        //TODO - Extraer el id del token recibido
        Long userId = 1L;

        restaurantService.disableRestaurant(userId,restaurantId);

        return ResponseEntity.ok().build();
    }


    @PutMapping("/enable/{restaurantId}")
    public ResponseEntity<Void> enableRestaurant(@PathVariable Long restaurantId){
        //TODO - Extraer el id del token recibido
        Long userId = 1L;

        restaurantService.enableRestaurant(userId,restaurantId);

        return ResponseEntity.ok().build();
    }

}

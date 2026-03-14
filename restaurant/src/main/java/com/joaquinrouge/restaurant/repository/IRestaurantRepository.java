package com.joaquinrouge.restaurant.repository;

import com.joaquinrouge.restaurant.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IRestaurantRepository extends JpaRepository<Restaurant,Long> {

    Optional<List<Restaurant>> findByUserId(Long userId);

}

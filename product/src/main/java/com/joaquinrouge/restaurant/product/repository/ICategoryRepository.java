package com.joaquinrouge.restaurant.product.repository;

import com.joaquinrouge.restaurant.product.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ICategoryRepository extends JpaRepository<Category,Long> {

    Optional<List<Category>> findByRestaurantId(Long restaurantId);

}

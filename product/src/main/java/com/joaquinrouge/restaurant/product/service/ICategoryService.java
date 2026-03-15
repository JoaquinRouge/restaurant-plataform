package com.joaquinrouge.restaurant.product.service;

import com.joaquinrouge.restaurant.product.dto.CreateOrUpdateCategoryDto;
import com.joaquinrouge.restaurant.product.model.Category;

import java.util.List;

public interface ICategoryService {

    List<Category> findByRestaurantId(Long restaurantId);
    Category findById(Long categoryId);
    Category createCategory(Long restaurantId,CreateOrUpdateCategoryDto data);
    void updateCategory(Long categoryId,Long restaurantId,CreateOrUpdateCategoryDto data);
    void deleteCategory(Long restaurantId,Long categoryId);

}

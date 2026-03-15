package com.joaquinrouge.restaurant.product.service;

import com.joaquinrouge.restaurant.product.dto.CreateOrUpdateCategoryDto;
import com.joaquinrouge.restaurant.product.exception.CategoryNotFoundException;
import com.joaquinrouge.restaurant.product.exception.InvalidCategoryException;
import com.joaquinrouge.restaurant.product.model.Category;
import com.joaquinrouge.restaurant.product.repository.ICategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements ICategoryService{

    private final ICategoryRepository categoryRepository;

    public CategoryService(ICategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> findByRestaurantId(Long restaurantId) {
        return categoryRepository.findByRestaurantId(restaurantId).orElseThrow(
                ()-> new CategoryNotFoundException("Categories not found for restaurant id " + restaurantId)
        );
    }

    @Override
    public Category findById(Long categoryId){
        return categoryRepository.findById(categoryId).orElseThrow(
                ()-> new CategoryNotFoundException("Category not found for id " + categoryId)
        );
    }

    @Override
    public Category createCategory(Long restaurantId, CreateOrUpdateCategoryDto data) {

        Category category = new Category();

        validate(data);

        category.setName(data.name());
        category.setRestaurantId(restaurantId);

        return categoryRepository.save(category);
    }

    @Override
    public void updateCategory(Long categoryId, Long restaurantId, CreateOrUpdateCategoryDto data) {

        validate(data);

        Category category = findById(categoryId);

        if(category.getRestaurantId().equals(restaurantId)) {
            category.setName(data.name());
        }

    }

    @Override
    public void deleteCategory(Long restaurantId, Long categoryId) {
        if(!categoryRepository.existsById(categoryId)){
            throw new CategoryNotFoundException("Category not found for id " + categoryId);
        }

        Category category = findById(categoryId);

        if(category.getRestaurantId().equals(restaurantId)){
            categoryRepository.deleteById(categoryId);
        }
    }

    private void validate(CreateOrUpdateCategoryDto data){
        if(data.name().isBlank()){
           throw new InvalidCategoryException("Invalid category data");
        }
    }

}

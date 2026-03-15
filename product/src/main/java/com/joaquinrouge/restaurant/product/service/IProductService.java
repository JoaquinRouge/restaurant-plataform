package com.joaquinrouge.restaurant.product.service;

import com.joaquinrouge.restaurant.product.dto.CreateOrUpdateProductDto;
import com.joaquinrouge.restaurant.product.model.Product;
import java.util.List;

public interface IProductService {

    public List<Product> findAll();
    public Product findById(Long id);
    Product createProduct(Long restaurantId,CreateOrUpdateProductDto productDto);
    void deleteProduct(Long restaurantId,Long id);
    void updateProduct(Long restaurantId,Long id, CreateOrUpdateProductDto productDto);

}

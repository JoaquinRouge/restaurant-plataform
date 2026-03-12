package com.joaquinrouge.restaurant.product.service;

import com.joaquinrouge.restaurant.product.dto.CreateOrUpdateProductDTO;
import com.joaquinrouge.restaurant.product.model.Product;
import java.util.List;

public interface IProductService {

    public List<Product> findAll();
    public Product findById(Long id);
    Product createProduct(CreateOrUpdateProductDTO productDto);
    void deleteProduct(Long id);
    void updateProduct(Long id,CreateOrUpdateProductDTO productDto);

}

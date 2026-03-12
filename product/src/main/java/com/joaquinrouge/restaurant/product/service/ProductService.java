package com.joaquinrouge.restaurant.product.service;

import com.joaquinrouge.restaurant.product.dto.CreateOrUpdateProductDTO;
import com.joaquinrouge.restaurant.product.exception.InvalidProductException;
import com.joaquinrouge.restaurant.product.exception.ProductNotFoundException;
import com.joaquinrouge.restaurant.product.model.Product;
import com.joaquinrouge.restaurant.product.repository.IProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService {

    private final IProductRepository productRepository;

    public ProductService(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found for id " + id));
    }

    @Override
    public Product createProduct(CreateOrUpdateProductDTO productDto) {
        validate(productDto);

        Product product = new Product();
        product.setName(productDto.name());
        product.setDescription(productDto.description());
        product.setPrice(productDto.price());

        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException("Product not found for id " + id);
        }

        productRepository.deleteById(id);
    }

    @Override
    public void updateProduct(Long id, CreateOrUpdateProductDTO productDto) {
        validate(productDto);

        Product product = findById(id);
        product.setName(productDto.name());
        product.setDescription(productDto.description());
        product.setPrice(productDto.price());

        productRepository.save(product);
    }

    private static void validate(CreateOrUpdateProductDTO productDto) {
        if (productDto == null) {
            throw new InvalidProductException("Product body is required");
        }

        if (productDto.name() == null || productDto.name().isBlank()) {
            throw new InvalidProductException("The product must have a name");
        }

        if (productDto.description() == null || productDto.description().isBlank()) {
            throw new InvalidProductException("The product must have a description");
        }

        if (productDto.price() <= 0) {
            throw new InvalidProductException("The price must be higher than 0");
        }
    }
}
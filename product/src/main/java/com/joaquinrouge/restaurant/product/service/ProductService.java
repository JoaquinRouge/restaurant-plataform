package com.joaquinrouge.restaurant.product.service;

import com.joaquinrouge.restaurant.product.dto.CreateOrUpdateProductDto;
import com.joaquinrouge.restaurant.product.exception.InvalidProductException;
import com.joaquinrouge.restaurant.product.exception.ProductNotFoundException;
import com.joaquinrouge.restaurant.product.model.Product;
import com.joaquinrouge.restaurant.product.repository.IProductRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class ProductService implements IProductService {

    private final IProductRepository productRepository;
    private final ICategoryService categoryService;

    public ProductService(IProductRepository productRepository,ICategoryService categoryService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
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
    public Product createProduct(Long restaurantId,CreateOrUpdateProductDto productDto) {
        validate(productDto);

        Product product = new Product();
        product.setName(productDto.name());
        product.setDescription(productDto.description());
        product.setPrice(productDto.price());
        product.setRestaurantId(restaurantId);
        product.setCategory(categoryService.findById(productDto.categoryId()));
        product.setCreatedAt(Instant.now());
        product.setSlug(generateSlug(productDto.name()));

        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long restaurantId,Long id) {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException("Product not found for id " + id);
        }

        productRepository.deleteById(id);
    }

    @Override
    public void updateProduct(Long restaurantId,Long id, CreateOrUpdateProductDto productDto) {
        validate(productDto);

        Product product = findById(id);
        product.setName(productDto.name());
        product.setDescription(productDto.description());
        product.setPrice(productDto.price());

        productRepository.save(product);
    }

    private static void validate(CreateOrUpdateProductDto productDto) {
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

    private String generateSlug(String text) {
        return text
                .toLowerCase()
                .trim()
                .replaceAll("[^a-z0-9\\s-]", "") // elimina caracteres raros
                .replaceAll("\\s+", "-"); // reemplaza espacios por -
    }
}
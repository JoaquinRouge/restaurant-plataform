package com.joaquinrouge.restaurant.product.controller;

import com.joaquinrouge.restaurant.product.dto.CreateOrUpdateProductDto;
import com.joaquinrouge.restaurant.product.model.Product;
import com.joaquinrouge.restaurant.product.service.IProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final IProductService productService;

    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> findAll() {
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody CreateOrUpdateProductDto productDto) {

        //TODO - extraer el restaurantId de los headers
        Long restaurantId = 1L;

        Product created = productService.createProduct(restaurantId,productDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody CreateOrUpdateProductDto productDto) {

        //TODO - extraer el restaurantId de los headers
        Long restaurantId = 1L;

        productService.updateProduct(restaurantId,id, productDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        //TODO - extraer el restaurantId de los headers
        Long restaurantId = 1L;

        productService.deleteProduct(restaurantId,id);
        return ResponseEntity.noContent().build();

    }

}
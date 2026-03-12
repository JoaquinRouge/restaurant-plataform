package com.joaquinrouge.restaurant.product.repository;

import com.joaquinrouge.restaurant.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepository extends JpaRepository<Product,Long> {
}

package com.joaquinrouge.restaurant.product.model;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(
        name = "products",
        uniqueConstraints = @UniqueConstraint(name = "uk_products_restaurant_slug", columnNames = {"restaurant_id", "slug"}),
        indexes = {
                @Index(name = "idx_products_restaurant_category", columnList = "restaurant_id,category_id"),
                @Index(name = "idx_products_restaurant_enabled", columnList = "restaurant_id,enabled")
        }
)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long restaurantId;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
    private String name;
    private String description;
    private double price;
    private String slug;
    private Instant createdAt;

    public Product(){

    }

    public Product(Long id, Long restaurantId, Category category, String name, String description, double price, String slug, Instant createdAt) {
        this.id = id;
        this.restaurantId = restaurantId;
        this.category = category;
        this.name = name;
        this.description = description;
        this.price = price;
        this.slug = slug;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}

package com.joaquinrouge.restaurant.product.dto;

public record CreateOrUpdateProductDto(Long categoryId, String name, String description, double price) {
}

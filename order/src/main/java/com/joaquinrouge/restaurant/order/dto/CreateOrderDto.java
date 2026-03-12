package com.joaquinrouge.restaurant.order.dto;

import java.util.List;

public record CreateOrderDto(String comment, List<ItemDto> items) {
}

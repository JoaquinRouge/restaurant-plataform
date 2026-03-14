package com.joaquinrouge.restaurant.order.dto;

import java.util.List;

public record CreateOrderDto(Long restaurantId,Long tableId,Long tableSessionId,String comment, List<ItemDto> items) {
}

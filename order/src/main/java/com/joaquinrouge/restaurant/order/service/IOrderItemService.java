package com.joaquinrouge.restaurant.order.service;

import com.joaquinrouge.restaurant.order.dto.ItemDto;
import com.joaquinrouge.restaurant.order.model.OrderItem;

import java.util.List;

public interface IOrderItemService {

    List<OrderItem> findByOrderId(Long orderId);
    void deleteOrderItem(Long id);
}

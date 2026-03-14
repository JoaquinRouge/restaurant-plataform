package com.joaquinrouge.restaurant.order.service;

import com.joaquinrouge.restaurant.order.dto.CreateOrderDto;
import com.joaquinrouge.restaurant.order.dto.ItemDto;
import com.joaquinrouge.restaurant.order.model.Order;

import java.util.List;

public interface IOrderService {

    List<Order> findAll(Long restaurantId);
    Order createOrder(CreateOrderDto orderData);
    Order updateOrder(Long id,List<ItemDto> items);
    void deleteOrder(Long id);

}

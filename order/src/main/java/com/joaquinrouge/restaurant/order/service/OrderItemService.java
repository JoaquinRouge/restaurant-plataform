package com.joaquinrouge.restaurant.order.service;

import com.joaquinrouge.restaurant.order.dto.ItemDto;
import com.joaquinrouge.restaurant.order.model.OrderItem;
import com.joaquinrouge.restaurant.order.repository.IOrderItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemService implements IOrderItemService{

    private final IOrderItemRepository orderItemRepository;

    public OrderItemService(IOrderItemRepository orderItemRepository){
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    public List<OrderItem> findByOrderId(Long orderId) {
        return orderItemRepository.findByOrder_Id(orderId);
    }

    @Override
    public void deleteOrderItem(Long id) {
        if(!orderItemRepository.existsById(id)){
            throw new IllegalArgumentException("Item not found for id " + id);
        }

        orderItemRepository.deleteById(id);
    }
}

package com.joaquinrouge.restaurant.order.service;

import com.joaquinrouge.restaurant.order.client.IProductClient;
import com.joaquinrouge.restaurant.order.dto.CreateOrderDto;
import com.joaquinrouge.restaurant.order.dto.ItemDto;
import com.joaquinrouge.restaurant.order.dto.ProductDto;
import com.joaquinrouge.restaurant.order.enums.OrderStatus;
import com.joaquinrouge.restaurant.order.exception.InvalidOrderException;
import com.joaquinrouge.restaurant.order.exception.OrderNotFoundException;
import com.joaquinrouge.restaurant.order.model.Order;
import com.joaquinrouge.restaurant.order.model.OrderItem;
import com.joaquinrouge.restaurant.order.repository.IOrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService implements IOrderService{

    private final IOrderRepository orderRepository;
    private final IProductClient productClient;

    public OrderService(IOrderRepository orderRepository,IProductClient productClient){
        this.orderRepository = orderRepository;
        this.productClient = productClient;
    }

    @Override
    public List<Order> findAll(Long restaurantId) {
        return orderRepository.findByRestaurantId(restaurantId).orElseThrow(
                ()-> new OrderNotFoundException("Orders not found for restaurant id " + restaurantId)
        );
    }

    @Override
    @Transactional
    public Order createOrder(CreateOrderDto orderData) {

        Order order = new Order();
        List<OrderItem> orderItems = new ArrayList<>();
        BigDecimal total = BigDecimal.ZERO;

        for(ItemDto item : orderData.items()){

            ProductDto product = productClient.findProductById(item.productId());

            if(item.quantity() <= 0 || product == null){
                throw new InvalidOrderException("Invalid order");
            }

             OrderItem orderItem = new OrderItem();
             orderItem.setProductId(product.getId());
             orderItem.setQuantity(item.quantity());
             orderItem.setOrder(order);

             orderItems.add(orderItem);

             total = total.add(product.getPrice().multiply(BigDecimal.valueOf(item.quantity())));

        }

        order.setRestaurantId(orderData.restaurantId());
        order.setTableId(orderData.tableId());
        order.setTableSessionId(orderData.tableSessionId());
        order.setItems(orderItems);
        order.setStatus(OrderStatus.CREATED);
        order.setTotal(total);
        order.setComment(orderData.comment());
        order.setCreatedAt(Instant.now());

        return orderRepository.save(order);
    }

    @Override
    public Order updateOrder(Long id, List<ItemDto> items) {
        return null;
    }

    @Override
    public void deleteOrder(Long id) {
        if(!orderRepository.existsById(id)){
            throw new OrderNotFoundException("Order not found for id " + id);
        }

        orderRepository.deleteById(id);
    }
}

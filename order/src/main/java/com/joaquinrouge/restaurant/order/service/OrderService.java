package com.joaquinrouge.restaurant.order.service;

import com.joaquinrouge.restaurant.order.client.IProductClient;
import com.joaquinrouge.restaurant.order.dto.CreateOrderDto;
import com.joaquinrouge.restaurant.order.dto.ItemDto;
import com.joaquinrouge.restaurant.order.dto.ProductDto;
import com.joaquinrouge.restaurant.order.exception.InvalidOrderException;
import com.joaquinrouge.restaurant.order.exception.OrderNotFoundException;
import com.joaquinrouge.restaurant.order.model.Order;
import com.joaquinrouge.restaurant.order.model.OrderItem;
import com.joaquinrouge.restaurant.order.repository.IOrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

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
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> findByUserId(Long userId){
        return orderRepository.findByUserId(userId).orElseThrow(
                ()-> new OrderNotFoundException("Order not found for user id " + userId)
        );
    }

    @Override
    @Transactional
    public Order createOrder(CreateOrderDto orderData) {

        Order order = new Order();
        List<OrderItem> orderItems = new ArrayList<>();
        double total = 0;

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

             total += product.getPrice();

        }

        order.setItems(orderItems);
        order.setComment(orderData.comment());
        order.setTotal(total);
        order.setDate(LocalDate.now());

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

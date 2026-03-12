package com.joaquinrouge.restaurant.order.controller;

import com.joaquinrouge.restaurant.order.dto.CreateOrderDto;
import com.joaquinrouge.restaurant.order.dto.ItemDto;
import com.joaquinrouge.restaurant.order.exception.InvalidOrderException;
import com.joaquinrouge.restaurant.order.exception.OrderNotFoundException;
import com.joaquinrouge.restaurant.order.model.Order;
import com.joaquinrouge.restaurant.order.service.IOrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final IOrderService orderService;

    public OrderController(IOrderService orderService){
        this.orderService = orderService;
    }

    @GetMapping()
    public ResponseEntity<List<Order>> findAll(){
        return ResponseEntity.ok(orderService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Order>> findByUserId(@PathVariable Long id){
        return ResponseEntity.ok(orderService.findByUserId(id));
    }

    @PostMapping()
    public ResponseEntity<Order> createOrder(@RequestBody CreateOrderDto orderData){
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(orderData));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateOrder(@PathVariable Long id,@RequestBody List<ItemDto> items){
        orderService.updateOrder(id,items);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}

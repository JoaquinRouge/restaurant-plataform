package com.joaquinrouge.restaurant.order.model;

import com.joaquinrouge.restaurant.order.enums.OrderStatus;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long restaurantId;
    private Long tableId;
    private Long tableSessionId;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> items;

    private OrderStatus status;
    private BigDecimal total;
    private String comment;
    private Instant createdAt;

    public Order(){

    }

    public Order(Long id, Long restaurantId, Long tableId, Long tableSessionId, List<OrderItem> items, OrderStatus status, BigDecimal total, String comment, Instant createdAt) {
        this.id = id;
        this.restaurantId = restaurantId;
        this.tableId = tableId;
        this.tableSessionId = tableSessionId;
        this.items = items;
        this.status = status;
        this.total = total;
        this.comment = comment;
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

    public Long getTableId() {
        return tableId;
    }

    public void setTableId(Long tableId) {
        this.tableId = tableId;
    }

    public Long getTableSessionId() {
        return tableSessionId;
    }

    public void setTableSessionId(Long tableSessionId) {
        this.tableSessionId = tableSessionId;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}

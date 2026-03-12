package com.joaquinrouge.restaurant.order.repository;

import com.joaquinrouge.restaurant.order.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrderItemRepository extends JpaRepository<OrderItem,Long> {

    List<OrderItem> findByOrder_Id(Long orderId);

}

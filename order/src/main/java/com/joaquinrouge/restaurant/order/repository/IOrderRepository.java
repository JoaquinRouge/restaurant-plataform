package com.joaquinrouge.restaurant.order.repository;

import com.joaquinrouge.restaurant.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IOrderRepository extends JpaRepository<Order,Long> {

    Optional<List<Order>> findByUserId(Long id);

}

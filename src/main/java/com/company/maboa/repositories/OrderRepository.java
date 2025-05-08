package com.company.maboa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.company.maboa.entities.Order;
import com.company.maboa.entities.OrderStatus;

import java.util.List;


public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByStatus(OrderStatus status);
    
    List<Order> findByStatusNotIn(List<OrderStatus> status);
}

package com.company.maboa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.company.maboa.entities.OrderItem;
import com.company.maboa.entities.OrderItemPK;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK>{

}

package com.dev.orderservice.orderservice.repository;

import com.dev.orderservice.orderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}

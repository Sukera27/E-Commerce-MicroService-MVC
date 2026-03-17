package com.sukera.orders_service.persistance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sukera.orders_service.persistance.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}

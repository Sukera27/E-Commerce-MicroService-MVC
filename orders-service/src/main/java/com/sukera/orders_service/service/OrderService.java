package com.sukera.orders_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sukera.orders_service.persistance.model.Order;
import com.sukera.orders_service.persistance.repository.OrderRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OrderService {

    OrderRepository orderRepository;

    public Order createOrder(Order order){
       return orderRepository.save(order);
    }

    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }
}

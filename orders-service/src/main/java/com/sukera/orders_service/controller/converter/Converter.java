package com.sukera.orders_service.controller.converter;

import org.springframework.stereotype.Component;

import com.sukera.orders_service.controller.dto.OrderDTO;
import com.sukera.orders_service.controller.dto.OrderItemDTO;
import com.sukera.orders_service.persistance.model.Order;
import com.sukera.orders_service.persistance.model.OrderItem;

@Component
public class Converter {

    public OrderDTO toOrderDTO(Order order){
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderNumber(order.getOrderNumber());
        orderDTO.setItems(order.getItems().stream().map(this::toOrderItemDTO).toList());
        return orderDTO;
    }

    private OrderItemDTO toOrderItemDTO(OrderItem item) {
        OrderItemDTO itemDTO = new OrderItemDTO();
        itemDTO.setProductCode(item.getProductCode());
        itemDTO.setQuantity(item.getQuantity());
        itemDTO.setPrice(item.getPrice());
        return itemDTO;
    }

    public Order toOrder(OrderDTO orderDTO){
        Order order = new Order();
        order.setOrderNumber(orderDTO.getOrderNumber());
        order.setItems(orderDTO.getItems().stream().map(this::toOrderItem).toList());
        return order;
    }

    private OrderItem toOrderItem(OrderItemDTO itemDTO) {
        OrderItem item = new OrderItem();
        item.setProductCode(itemDTO.getProductCode());
        item.setQuantity(itemDTO.getQuantity());
        item.setPrice(itemDTO.getPrice());
        return item;
    }
}
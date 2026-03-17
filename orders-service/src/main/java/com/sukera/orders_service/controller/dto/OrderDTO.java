package com.sukera.orders_service.controller.dto;

import java.util.List;

import lombok.Data;

@Data
public class OrderDTO {

    String orderNumber;
    List<OrderItemDTO> items;

    
}

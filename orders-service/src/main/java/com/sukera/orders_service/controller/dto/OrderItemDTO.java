package com.sukera.orders_service.controller.dto;

import lombok.Data;

@Data
public class OrderItemDTO {
    Long id;
    String productCode;  
    Integer quantity;  
    Double price;
}

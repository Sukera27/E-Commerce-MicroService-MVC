package com.sukera.products_service.controller.dto;

import lombok.Data;

@Data
public class ProductDTO {
    
    String name;
    String description;
    String productCode;
    Double price;   
    Boolean status;
}

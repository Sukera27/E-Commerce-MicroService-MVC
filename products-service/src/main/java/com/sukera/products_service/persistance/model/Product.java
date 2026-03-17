package com.sukera.products_service.persistance.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@NoArgsConstructor
@Table(name = "products")
public class Product {


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "product_id")
    Long id;
    @Column(name = "product_name")
    String name;
    @Column(name = "product_code")
    String productCode;
    @Column(name = "product_description")
    String description;
    @Column(name = "product_price")
    Double price;   
    @Column(name = "product_status")
    Boolean status;


}

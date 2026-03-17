package com.sukera.orders_service.persistance.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    Long id;

    @Column(name = "order_item_product_code")
    String productCode;
    @Column(name = "order_item_quantity")
    Integer quantity;
    @Column(name = "order_item_price")
    Double price;

    @ManyToOne
    @JoinColumn(name = "order_id")
    Order order;
}

package com.sukera.products_service.persistance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sukera.products_service.persistance.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}

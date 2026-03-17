package com.sukera.products_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sukera.products_service.persistance.model.Product;
import com.sukera.products_service.persistance.repository.ProductRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductService {

    ProductRepository productRepository;


    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }
}

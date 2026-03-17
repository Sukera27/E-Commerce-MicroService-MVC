package com.sukera.products_service.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sukera.products_service.controller.converter.Converter;
import com.sukera.products_service.controller.dto.ProductDTO;
import com.sukera.products_service.persistance.model.Product;
import com.sukera.products_service.service.ProductService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/products/")
@AllArgsConstructor
@Slf4j
public class ProductController {

    ProductService productService;

    Converter converter;

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts(){
        return ResponseEntity.ok().body(productService.getAllProducts().stream().map(converter::toProductDTO).toList());
    }

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO){
        Product product = converter.toProduct(productDTO);
        Product saved = productService.createProduct(product);
        return ResponseEntity.ok().body(converter.toProductDTO(saved));
    }
}

package com.sukera.products_service.controller.converter;

import org.springframework.stereotype.Component;

import com.sukera.products_service.controller.dto.ProductDTO;
import com.sukera.products_service.persistance.model.Product;

@Component
public class Converter {

    public ProductDTO toProductDTO(Product product){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        productDTO.setStatus(product.getStatus());
        productDTO.setProductCode(product.getProductCode());
        return productDTO;
    }

    public Product toProduct(ProductDTO productDTO){
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setStatus(productDTO.getStatus());
        product.setProductCode(productDTO.getProductCode());
        return product;
    }
}

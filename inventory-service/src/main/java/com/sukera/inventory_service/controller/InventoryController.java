package com.sukera.inventory_service.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sukera.inventory_service.controller.dto.InventoryResponse;
import com.sukera.inventory_service.controller.dto.OrderItemDTO;
import com.sukera.inventory_service.service.InventoryService;

import lombok.AllArgsConstructor;

@RestController
@CrossOrigin
@AllArgsConstructor
@RequestMapping("/api/v1/inventory/")
public class InventoryController {
    
    InventoryService inventoryService;

    @GetMapping("/{productCode}")
    public boolean isInStock(@PathVariable String productCode){
        return inventoryService.isInStock(productCode);
    }

    @PostMapping("/in-stock")
    public ResponseEntity<List<InventoryResponse>> checkStock(
            @RequestBody List<OrderItemDTO> orderItemsDTO){
        try {
        return ResponseEntity.ok(inventoryService.areInStock(orderItemsDTO));
    } catch (Exception e) {
        e.printStackTrace(); // imprime la causa exacta
        return ResponseEntity.status(500).body(null);
    }     
    }

}

package com.sukera.inventory_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sukera.inventory_service.controller.dto.InventoryResponse;
import com.sukera.inventory_service.controller.dto.OrderItemDTO;
import com.sukera.inventory_service.persistance.model.Inventory;
import com.sukera.inventory_service.persistance.repository.InventoryRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class InventoryService {

    InventoryRepository inventoryRepository;


    public boolean isInStock(String productCode){
        return inventoryRepository.findByProductCode(productCode).isPresent();
    }

    public List<InventoryResponse> areInStock(List<OrderItemDTO> orderItemsDTO){
        return orderItemsDTO.stream()
                .map(item -> new InventoryResponse(
                        item.getProductCode(),
                        isInStock(item.getProductCode())
                ))
                .toList();
    }
    // Método para crear inventario inicial
    public Inventory addInventory(String productCode, int quantity) {
        Inventory inv = new Inventory();
        inv.setProductCode(productCode);
        inv.setQuantity(quantity);
        return inventoryRepository.save(inv);
    }
}

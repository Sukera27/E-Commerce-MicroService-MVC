package com.sukera.inventory_service.utils;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.sukera.inventory_service.service.InventoryService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
     final InventoryService inventoryService;

    @Override
    public void run(String... args) throws Exception {
        inventoryService.addInventory("PROD-1", 10);
        inventoryService.addInventory("PROD-2", 0);
        inventoryService.addInventory("PROD-3", 5);
    }
}

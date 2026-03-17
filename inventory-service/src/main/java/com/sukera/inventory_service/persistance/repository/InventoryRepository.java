package com.sukera.inventory_service.persistance.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sukera.inventory_service.persistance.model.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    public Optional<Inventory> findByProductCode(String productCode);
}

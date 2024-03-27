package com.example.inventoryservice.service;

import com.example.inventoryservice.entity.Inventory;
import com.example.inventoryservice.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    public InventoryService(@Autowired InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Transactional(readOnly = true)
    public boolean isPresent(String code) {
        Optional<Inventory> byCode = inventoryRepository.findByCode(code);
        return byCode.isPresent();
    }
}

package com.product.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.product.entity.Item;
import com.product.services.ItemServices;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ItemController {

    private final ItemServices itemService;

    @PostMapping("/{id}/items")
    @PreAuthorize("hasRole('ADMIN')")
    public Item addItem(@PathVariable Long id, @RequestBody Item item) {
        return itemService.addItem(id, item);
    }
}
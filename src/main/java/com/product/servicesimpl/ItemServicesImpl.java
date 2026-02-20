package com.product.servicesimpl;

import org.springframework.stereotype.Service;

import com.product.entity.Item;
import com.product.entity.Product;
import com.product.exception.ResourceNotFoundException;
import com.product.repo.ItemRepo;
import com.product.repo.ProductRepo;
import com.product.services.ItemServices;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ItemServicesImpl implements ItemServices {

    private final ItemRepo itemRepo;
    private final ProductRepo productRepo;

    @Override
    public Item addItem(Long productId, Item item) {

        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        item.setProduct(product);

        return itemRepo.save(item);
    }
}
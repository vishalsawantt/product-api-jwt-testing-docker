package com.product.servicesimpl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.product.entity.Item;
import com.product.entity.Product;
import com.product.exception.ResourceNotFoundException;
import com.product.repo.ItemRepo;
import com.product.repo.ProductRepo;
import com.product.services.ProductServices;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServicesImpl implements ProductServices {

    private final ProductRepo productRepo;
    private final ItemRepo itemRepo;

    @Override
    public Page<Product> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productRepo.findAll(pageable);
    }

    @Override
    public Product getById(Long id) {
        return productRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    }

    @Override
    public Product create(Product product) {
        return productRepo.save(product);
    }

    @Override
    public Product update(Long id, Product product) {
        Product existing = getById(id);
        existing.setProductName(product.getProductName());
        return productRepo.save(existing);
    }

    @Override
    public void delete(Long id) {
        productRepo.deleteById(id);
    }

    @Override
    public List<Item> getItems(Long productId) {
        return itemRepo.findByProductId(productId);
    }
}
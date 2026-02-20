package com.product.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.product.entity.Item;
import com.product.entity.Product;

public interface ProductServices {

	Page<Product> getAll(int page, int size);

    Product getById(Long id);

    Product create(Product product);

    Product update(Long id, Product product);

    void delete(Long id);

    List<Item> getItems(Long productId);
}

package com.product.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.product.entity.Item;

public interface ItemRepo extends JpaRepository<Item, Long> {
    List<Item> findByProductId(Long productId);
}

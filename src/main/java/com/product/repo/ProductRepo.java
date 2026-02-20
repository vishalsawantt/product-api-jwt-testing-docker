package com.product.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.product.entity.Product;

public interface ProductRepo extends JpaRepository<Product, Long> {
	
}


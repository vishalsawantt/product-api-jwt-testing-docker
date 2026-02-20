package com.product;

import com.product.entity.Product;
import com.product.repo.ProductRepo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class ProductIntegrationTest {

    @Autowired
    private ProductRepo productRepo;

    @Test
    void testSaveProduct() {

        Product product = new Product();
        product.setProductName("Integration Test Product");

        Product saved = productRepo.save(product);

        assertNotNull(saved.getId());
        assertEquals("Integration Test Product", saved.getProductName());
    }
}
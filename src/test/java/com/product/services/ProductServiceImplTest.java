package com.product.services;

import com.product.entity.Product;
import com.product.repo.ProductRepo;
import com.product.servicesimpl.ProductServicesImpl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepo productRepo;

    @InjectMocks
    private ProductServicesImpl productService;

    @Test
    void testGetAllProducts() {

        Product product = new Product();
        product.setProductName("Laptop");

        Page<Product> page = new PageImpl<>(List.of(product));

        when(productRepo.findAll(PageRequest.of(0,1))).thenReturn(page);

        Page<Product> result = productService.getAll(0,1);

        assertNotNull(result);
        assertEquals(1, result.getContent().size());

        verify(productRepo).findAll(PageRequest.of(0,1));
    }

    @Test
    void testCreateProduct() {

        Product product = new Product();
        product.setProductName("Mobile");

        when(productRepo.save(product)).thenReturn(product);

        Product saved = productService.create(product);

        assertEquals("Mobile", saved.getProductName());
        verify(productRepo).save(product);
    }
}
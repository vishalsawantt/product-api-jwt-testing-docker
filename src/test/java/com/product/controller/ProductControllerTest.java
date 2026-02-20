package com.product.controller;

import com.product.entity.Product;
import com.product.services.ProductServices;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductControllerTest {

    @Test
    void testGetAllProducts() {

        ProductServices service = Mockito.mock(ProductServices.class);

        ProductController controller = new ProductController(service);

        Product product = new Product();
        product.setProductName("Pen");

        Page<Product> page = new PageImpl<>(List.of(product));

        Mockito.when(service.getAll(0,5)).thenReturn(page);

        Page<Product> result = controller.getAll(0,5);

        assertEquals(1, result.getContent().size());
    }
}
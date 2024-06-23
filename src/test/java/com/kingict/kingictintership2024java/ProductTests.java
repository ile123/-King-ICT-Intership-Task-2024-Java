package com.kingict.kingictintership2024java;

import com.kingict.kingictintership2024java.model.Product;
import com.kingict.kingictintership2024java.repository.IProductRepository;
import com.kingict.kingictintership2024java.service.IProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
public class ProductTests {

    @Autowired
    private IProductService productService;

    @Autowired
    private IProductRepository productRepository;

    @BeforeEach
    void setUpDb() {
        productRepository.deleteAll();
        
        var product1 = Product.builder()
                .title("test1")
                .price(2.2f)
                .description("test1")
                .category("test1")
                .sku("test1")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .images(new HashSet<>())
                .build();

        var product2 = Product.builder()
                .title("test2")
                .price(4.2f)
                .description("test2")
                .category("test2")
                .sku("test2")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .images(new HashSet<>())
                .build();

        productRepository.save(product1);
        productRepository.save(product2);
    }

    @Test
    void getAllProducts() {
        var products = productService.getAllProducts();
        assertEquals(2, products.result().size(), "Correct number of products");
    }
    
    @Test
    void getProductById() {
        var products = productService.getAllProducts();
        var product = productRepository.getReferenceById(products.result().get(0).id());
        assertEquals(products.result().get(0).id(), product.getId(), "Product with id found");
    }
    
    @Test
    void getProductsByCategoryAndPrice() {
        var products = productService.getAllProductsByCategoryAndPrice("test1", 3.3f);
        assertEquals(1, products.result().size(), "Correct number of products by category and price");
    }
    
    @Test
    void getProductsByName() {
        var products = productService.getAllProductsByName("test");
        assertEquals(2, products.result().size(), "Correct number of products by category and price");
    }
}

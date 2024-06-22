package com.kingict.kingictintership2024java.controller;

import com.kingict.kingictintership2024java.dto.ApiResponse;
import com.kingict.kingictintership2024java.dto.ProductDto;
import com.kingict.kingictintership2024java.service.IProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {
    
    private final IProductService productService;

    public ProductController(IProductService productService) {
        this.productService = productService;
    }
    
    @GetMapping("")
    public ResponseEntity<ApiResponse<List<ProductDto>>> getAllProducts() {
        try {
            var allProducts = productService.getAllProducts();
            return ResponseEntity.ok(allProducts);
        } catch (Exception e) {
            return ResponseEntity
                    .internalServerError()
                    .body(new ApiResponse<>(false, e.getMessage(), new ArrayList<>()));
        }
    }

    @GetMapping("/name")
    public ResponseEntity<ApiResponse<List<ProductDto>>> getAllProductsByName(
            @RequestParam(defaultValue = "") String name) {
        try {
            var allProducts = productService.getAllProductsByName(name);
            return ResponseEntity.ok(allProducts);
        } catch (Exception e) {
            return ResponseEntity
                    .internalServerError()
                    .body(new ApiResponse<>(false, e.getMessage(), new ArrayList<>()));
        }
    }

    @GetMapping("/category-and-price")
    public ResponseEntity<ApiResponse<List<ProductDto>>> getAllProductsByCategoryAndName(
            @RequestParam(defaultValue = "groceries") String category, 
            @RequestParam(defaultValue = "1000") float price) {
        try {
            var allProducts = productService.getAllProductsByCategoryAndPrice(category, price);
            return ResponseEntity.ok(allProducts);
        } catch (Exception e) {
            return ResponseEntity
                    .internalServerError()
                    .body(new ApiResponse<>(false, e.getMessage(), new ArrayList<>()));
        }
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ApiResponse<ProductDto>> getAllProductsByName(@PathVariable("productId") UUID productId) {
        try {
            var product = productService.getProductById(productId);
            return ResponseEntity.ok(product);
        } catch (Exception e) {
            return ResponseEntity
                    .internalServerError()
                    .body(new ApiResponse<>(false,
                            e.getMessage(),
                            new ProductDto(UUID.randomUUID(), "", 0.0f, "", "", "", new ArrayList<>())));
        }
    }
    
    
}

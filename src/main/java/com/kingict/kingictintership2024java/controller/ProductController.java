package com.kingict.kingictintership2024java.controller;

import com.kingict.kingictintership2024java.dto.ApiResponse;
import com.kingict.kingictintership2024java.dto.ProductDto;
import com.kingict.kingictintership2024java.service.IProductService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {
    
    private final IProductService productService;
    private final Logger logger = LogManager.getLogger(ProductController.class);

    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    /**
     * Retrieves all products from the db.
     *
     * @return ResponseEntity with all the products from the db.
     **/
    @GetMapping("")
    public ResponseEntity<ApiResponse<List<ProductDto>>> getAllProducts() {
        try {
            var allProducts = productService.getAllProducts();
            return ResponseEntity.ok(allProducts);
        } catch (Exception e) {
            logger.log(Level.ERROR, e.getMessage());
            return ResponseEntity
                    .internalServerError()
                    .body(new ApiResponse<>(false, e.getMessage(), new ArrayList<>()));
        }
    }

    /**
     * Retrieves all products with same or similar name from the db.
     *
     * @param name The exact/similar name of the products.
     * @return ResponseEntity with the found products.
     **/
    @GetMapping("/name")
    public ResponseEntity<ApiResponse<List<ProductDto>>> getAllProductsByName(
            @RequestParam(defaultValue = "") String name) {
        try {
            var allProducts = productService.getAllProductsByName(name);
            return ResponseEntity.ok(allProducts);
        } catch (Exception e) {
            logger.log(Level.ERROR, e.getMessage());
            return ResponseEntity
                    .internalServerError()
                    .body(new ApiResponse<>(false, e.getMessage(), new ArrayList<>()));
        }
    }

    /**
     * Retrieves all products with same category and price that is the same/lower from the db.
     *
     * @param category The category of the product.
     * @param price The price of the product.
     * @return ResponseEntity with the found products.
     **/
    @GetMapping("/category-and-price")
    public ResponseEntity<ApiResponse<List<ProductDto>>> getAllProductsByCategoryAndName(
            @RequestParam(defaultValue = "groceries") String category, 
            @RequestParam(defaultValue = "1000") float price) {
        try {
            var allProducts = productService.getAllProductsByCategoryAndPrice(category, price);
            return ResponseEntity.ok(allProducts);
        } catch (Exception e) {
            logger.log(Level.ERROR, e.getMessage());
            return ResponseEntity
                    .internalServerError()
                    .body(new ApiResponse<>(false, e.getMessage(), new ArrayList<>()));
        }
    }

    /**
     * Retrieves the product from the db with the given id.
     *
     * @param id The id of the product.
     * @return ResponseEntity with the found product.
     **/
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductDto>> getAllProductsByName(@PathVariable("id") UUID id) {
        try {
            var product = productService.getProductById(id);
            return ResponseEntity.ok(product);
        } catch (Exception e) {
            logger.log(Level.ERROR, e.getMessage());
            return ResponseEntity
                    .internalServerError()
                    .body(new ApiResponse<>(false,
                            e.getMessage(),
                            new ProductDto(UUID.randomUUID(), "", 0.0f, "", "", "", new ArrayList<>())));
        }
    }
    
    
}

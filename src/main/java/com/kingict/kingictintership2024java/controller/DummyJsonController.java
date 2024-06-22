package com.kingict.kingictintership2024java.controller;

import com.kingict.kingictintership2024java.dto.ApiResponse;
import com.kingict.kingictintership2024java.dto.ProductDto;
import com.kingict.kingictintership2024java.service.IExternalApiService;
import com.kingict.kingictintership2024java.service.IProductService;
import com.kingict.kingictintership2024java.service.implementations.DummyJsonService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.UUID;

@RestController
@RequestMapping("/dummy-json")
public class DummyJsonController {
    
    private final DummyJsonService externalApiService;
    private final IProductService productService;

    public DummyJsonController(DummyJsonService externalApiService, IProductService productService) {
        this.externalApiService = externalApiService;
        this.productService = productService;
    }

    @GetMapping("")
    public ResponseEntity<ApiResponse<String>> GetDataFromApi() {
        try {
            var externalApiProducts = externalApiService.getDataFromApi();
            if(externalApiProducts.isEmpty()) throw new Exception("ERROR: Fetching data from a external API has failed!");
            for(var product : externalApiProducts) {
                productService.saveProduct(product);
            }
            return ResponseEntity.ok(new ApiResponse<>(true,
                    "All products retrieved!",
                    "Products from Dummy Json API have been retrieved successfully"));
        } catch (Exception e) {
            return ResponseEntity
                    .internalServerError()
                    .body(new ApiResponse<>(false,
                            e.getMessage(),
                           "Cannot get data from Dummy Json API!"));
        }
    } 
}

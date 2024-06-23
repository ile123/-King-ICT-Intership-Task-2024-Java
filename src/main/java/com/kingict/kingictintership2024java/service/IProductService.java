package com.kingict.kingictintership2024java.service;

import com.kingict.kingictintership2024java.dto.AddProductDto;
import com.kingict.kingictintership2024java.dto.ApiResponse;
import com.kingict.kingictintership2024java.dto.ProductDto;
import com.kingict.kingictintership2024java.model.Product;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IProductService {
    ApiResponse<List<ProductDto>> getAllProducts();
    ApiResponse<List<ProductDto>> getAllProductsByName(String title);
    ApiResponse<List<ProductDto>> getAllProductsByCategoryAndPrice(String category, float price);
    ApiResponse<ProductDto> getProductById(UUID id);
    void saveProduct(AddProductDto addProductDto);
    boolean validateProduct(AddProductDto product);
}

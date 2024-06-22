package com.kingict.kingictintership2024java.service.implementations;

import com.kingict.kingictintership2024java.dto.AddProductDto;
import com.kingict.kingictintership2024java.dto.ApiResponse;
import com.kingict.kingictintership2024java.dto.ProductDto;
import com.kingict.kingictintership2024java.mapper.ProductMapper;
import com.kingict.kingictintership2024java.model.Image;
import com.kingict.kingictintership2024java.model.Product;
import com.kingict.kingictintership2024java.repository.IProductRepository;
import com.kingict.kingictintership2024java.service.IProductService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductService implements IProductService {
    
    private final IProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductService(IProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public ApiResponse<List<ProductDto>> getAllProducts() {
        var products = productRepository.findAll();
        var productDtos = products
                .stream()
                .map(productMapper::toProductDto)
                .toList();
        return new ApiResponse<>(true, "All products retrieved", productDtos);
    }

    @Override
    public ApiResponse<List<ProductDto>> getAllProductsByName(String title) {
        var products = productRepository.findAllProductsByTitle(title);
        var productDtos = products
                .stream()
                .map(productMapper::toProductDto)
                .toList();
        return new ApiResponse<>(true, "All products by name retrieved", productDtos);
    }

    @Override
    public ApiResponse<List<ProductDto>> getAllProductsByCategoryAndPrice(String category, float price) {
        var products = productRepository.findAllProductsByCategoryAndPrice(category, price);
        var productDtos = products
                .stream()
                .map(productMapper::toProductDto)
                .toList();
        return new ApiResponse<>(false, "All products by category and price retrieved", productDtos);
    }

    @Override
    public ApiResponse<ProductDto> getProductById(UUID id) {
        try {
            var product = productRepository
                    .findById(id)
                    .orElseThrow(() -> new Exception("ERROR: Product with given id not found -> "));
            return new ApiResponse<>(true, "Product with given id found", productMapper.toProductDto(product));
        } catch (Exception e) {
            return new ApiResponse<>(false, e.getMessage(), new ProductDto(UUID.randomUUID(), "", 0.0f, "", "", "", new ArrayList<>()));
        }
    }

    @Override
    public void saveProduct(AddProductDto addProductDto) {
        if(productRepository.doesProductWithSkuExist(addProductDto.sku()).isPresent()) return;
        var product = Product
                .builder()
                .title(addProductDto.title())
                .price(addProductDto.price())
                .description(addProductDto.description())
                .category(addProductDto.category())
                .sku(addProductDto.sku())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .images(new HashSet<>())
                .build();
        
        var images = addProductDto
                .images()
                .stream()
                .map(imageUrl -> Image.builder()
                        .imageUrl(imageUrl)
                        .createdAt(LocalDateTime.now())
                        .product(product)
                        .build())
                .collect(Collectors.toSet());
        
        product
                .getImages()
                .addAll(images);
        
        productRepository.save(product);
    }

    @Override
    public boolean validateProduct(Product product) {
        if(product.getTitle().isBlank() || product.getTitle().isEmpty()) return false;
        if(product.getDescription().isBlank() || product.getDescription().isEmpty() || product.getDescription().length() > 100) return false;
        if(product.getCategory().isBlank() || product.getCategory().isEmpty()) return false;
        if(product.getSku().isBlank() || product.getSku().isEmpty()) return false;
        if(product.getImages().isEmpty()) return false;
        return true;
    }
}

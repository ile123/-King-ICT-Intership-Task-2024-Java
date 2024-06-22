package com.kingict.kingictintership2024java.mapper;

import com.kingict.kingictintership2024java.dto.AddProductDto;
import com.kingict.kingictintership2024java.dto.ProductDto;
import com.kingict.kingictintership2024java.model.Image;
import com.kingict.kingictintership2024java.model.Product;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ProductMapper {

    public ProductDto toProductDto(Product product) {
        return new ProductDto(product.getId(), 
                product.getTitle(), 
                product.getPrice(), 
                product.getDescription(), 
                product.getCategory(),
                product.getSku(),
                product.getImages()
                        .stream()
                        .map(Image::getImageUrl)
                        .collect(Collectors.toList()));
    }
}

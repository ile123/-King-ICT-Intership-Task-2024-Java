package com.kingict.kingictintership2024java.repository;

import com.kingict.kingictintership2024java.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface IProductRepository extends JpaRepository<Product, UUID> {
    @Query("SELECT x FROM Product x WHERE x.title = :title")
    List<Product> findAllProductsByTitle(@Param("title") String title);
    
    @Query("SELECT x FROM Product x WHERE x.category = :category AND x.price <= :price")
    List<Product> findAllProductsByCategoryAndPrice(@Param("category")String category, @Param("price")float price);
    
    @Query("SELECT x FROM Product x WHERE x.sku = :sku")
    Optional<Product> doesProductWithSkuExist(@Param("sku") String sku);
}

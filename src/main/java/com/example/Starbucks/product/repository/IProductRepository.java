package com.example.Starbucks.product.repository;

import com.example.Starbucks.category.projection.IProduct;
import com.example.Starbucks.product.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IProductRepository extends JpaRepository<Product, Long> {
    @Query(value = "select p.id, p.name, p.price, p.thumbnail " +
            "from Product p"
            , countQuery = "select count(*) from product"
            , nativeQuery = true)
    Page<IProduct> getAllProduct(Pageable pageable);

    @Query(value = "select p.id, p.name, p.price, p.thumbnail " +
            "from Product p " +
            "where p.id > :productId " +
            "limit 8"
            , nativeQuery = true)
    List<IProduct> getAllProduct(@Param("productId") Long productId);
}

package com.example.Starbucks.product.repository;

import com.example.Starbucks.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepository extends JpaRepository<Product,Long> {
}

package com.example.Starbucks.product.repository;

import com.example.Starbucks.product.model.ProductCategoryList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IProductCategoryListRepository extends JpaRepository<ProductCategoryList,Long> {

    List<ProductCategoryList> findAllByProductId(Long productId);
}

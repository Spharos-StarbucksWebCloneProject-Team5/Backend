package com.example.Starbucks.category.repository;

import com.example.Starbucks.category.model.CategoryList;
import com.example.Starbucks.category.model.ProductList;
import com.example.Starbucks.category.projection.IProduct;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SearchRepository extends CrudRepository<ProductList, String> {
    ProductList save(ProductList productList);
//    List<IProduct> saveAll(Li);
}

package com.example.Starbucks.product.service;

import com.example.Starbucks.product.model.Product;

import java.util.List;

public interface IProductService {

    void addProduct(Product product);
    Product getProduct(Long productId);
    List<Product> getAllProduct();

}

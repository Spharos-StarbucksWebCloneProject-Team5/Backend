package com.example.Starbucks.product.service;

import com.example.Starbucks.product.model.Product;
import com.example.Starbucks.product.vo.RequestProduct;
import com.example.Starbucks.product.vo.ResponseProduct;

import java.util.List;

public interface IProductService {

    void addProduct(RequestProduct requestProduct);
    ResponseProduct getProduct(Long productId);
    List<ResponseProduct> getAllProduct();
    void updateProduct(Long id, RequestProduct requestProduct);
    void deleteProduct(Long id);

}

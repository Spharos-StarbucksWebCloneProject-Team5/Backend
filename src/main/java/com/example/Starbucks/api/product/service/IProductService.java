package com.example.Starbucks.api.product.service;

import com.example.Starbucks.api.product.vo.RequestProduct;
import com.example.Starbucks.api.product.vo.ResponseProduct;
import com.example.Starbucks.api.category.dto.ResponseSearch;

import java.util.List;

public interface IProductService {

    void addProduct(RequestProduct requestProduct);
    ResponseProduct getProduct(Long productId);
    List<ResponseSearch> getAllProduct2(Long lastProduct);
    void updateProduct(Long id, RequestProduct requestProduct);
    void deleteProduct(Long id);

}

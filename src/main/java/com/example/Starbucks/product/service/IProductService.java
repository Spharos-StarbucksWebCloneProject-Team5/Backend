package com.example.Starbucks.product.service;

import com.example.Starbucks.category.dto.ResponsePage;
import com.example.Starbucks.category.dto.ResponseSearch;
import com.example.Starbucks.product.dto.ResponseProductList;
import com.example.Starbucks.product.model.Product;
import com.example.Starbucks.product.vo.RequestProduct;
import com.example.Starbucks.product.vo.ResponseProduct;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProductService {

    void addProduct(RequestProduct requestProduct);
    ResponseProduct getProduct(Long productId);
    ResponsePage getAllProduct(int pageNum, Pageable pageable);
    List<ResponseSearch> getAllProduct2(Long lastProduct);
    void updateProduct(Long id, RequestProduct requestProduct);
    void deleteProduct(Long id);

}

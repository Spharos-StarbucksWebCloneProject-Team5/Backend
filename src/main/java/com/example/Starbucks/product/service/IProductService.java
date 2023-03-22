package com.example.Starbucks.product.service;

import com.example.Starbucks.product.dto.ResponseProductList;
import com.example.Starbucks.product.model.Product;
import com.example.Starbucks.product.vo.RequestProduct;
import com.example.Starbucks.product.vo.ResponseProduct;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProductService {

    void addProduct(RequestProduct requestProduct);
    ResponseProduct getProduct(Long productId);
    List<ResponseProductList> getAllProduct(int pageNum, Pageable pageable);
    void updateProduct(Long id, RequestProduct requestProduct);
    void deleteProduct(Long id);

}

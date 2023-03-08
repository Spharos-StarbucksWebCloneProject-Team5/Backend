package com.example.Starbucks.product.service;

import com.example.Starbucks.product.model.ProductImageList;

import java.util.List;

public interface IProductImageListService {
    void addProductImage(ProductImageList productImageList);


    List<ProductImageList> getByProductId(Long productId);

    List<ProductImageList> getAll() ;
    void updateProductImageList(ProductImageList productImageList);
}

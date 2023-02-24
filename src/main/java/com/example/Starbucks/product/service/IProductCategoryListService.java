package com.example.Starbucks.product.service;

import com.example.Starbucks.product.model.ProductCategoryList;
import com.example.Starbucks.product.vo.RequestProductCategoryList;

import java.util.List;

public interface IProductCategoryListService {

    void addProductCategoryList(RequestProductCategoryList requestProductCategoryList);
    List<ProductCategoryList> getByProductId(Long productId);
}

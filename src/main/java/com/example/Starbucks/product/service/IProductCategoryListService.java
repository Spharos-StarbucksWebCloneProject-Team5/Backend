package com.example.Starbucks.product.service;

import com.example.Starbucks.product.model.ProductCategoryList;
import com.example.Starbucks.product.vo.RequestProductCategoryList;

import java.util.List;

public interface IProductCategoryListService {

    void addProductCategoryList(RequestProductCategoryList requestProductCategoryList);
    List<ProductCategoryList> getByProductId(Long productId);
    List<ProductCategoryList> getByCategoryId(Integer categoryId);
    List<ProductCategoryList> getAll();
    List<ProductCategoryList> getByMiddleCategoryId(Integer categoryId, Integer middleCategoryId);
    //List<ProductCategoryList> getByCategory(Integer categoryId,Integer middleCategoryId);
    List<ProductCategoryList> getCategory(Integer categoryId, Integer middleCategoryId);

}

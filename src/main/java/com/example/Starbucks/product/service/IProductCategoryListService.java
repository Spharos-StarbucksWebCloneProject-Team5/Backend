package com.example.Starbucks.product.service;

import com.example.Starbucks.category.model.CategoryList;
import com.example.Starbucks.category.vo.RequestProductCategoryList;

import java.util.List;

public interface IProductCategoryListService {

    void addProductCategoryList(RequestProductCategoryList requestProductCategoryList);
    List<CategoryList> getByProductId(Long productId);
    List<CategoryList> getByCategoryId(Integer categoryId);
    List<CategoryList> getAll();
    List<CategoryList> getByMiddleCategoryId(Integer categoryId, Integer middleCategoryId);
    //List<ProductCategoryList> getByCategory(Integer categoryId,Integer middleCategoryId);
    List<CategoryList> getCategory(Integer categoryId, Integer middleCategoryId);

}

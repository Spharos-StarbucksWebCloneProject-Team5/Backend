package com.example.Starbucks.product.service;

import com.example.Starbucks.product.model.MainCategory;
import com.example.Starbucks.product.vo.RequestCategory;
import com.example.Starbucks.product.vo.ResponseCategoryList;
import org.apache.coyote.Response;

import java.util.List;

public interface ICategoryListService {

    void addCategory(RequestCategory requestCategory);
    MainCategory getCategory(Integer categoryId);
    List<MainCategory> getAll();
    //List<CategoryTypeDto> getAllType(String categoryType) ;
    void updateCategory(MainCategory mainCategory);

    List<ResponseCategoryList.categorySearchInfo> searchByCategory(Integer mainCategoryId, Integer middleCategoryInteger);

    //List<String> getCategoryTypeName();
}

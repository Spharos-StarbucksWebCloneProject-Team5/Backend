package com.example.Starbucks.category.service;

import com.example.Starbucks.category.model.MainCategory;
import com.example.Starbucks.category.dto.ResponseMainCategory;

import java.util.List;

public interface MainCategoryService {
    List<ResponseMainCategory.Side> getAllSideMainCategories();
    List<ResponseMainCategory> getAllMainCategories();
    MainCategory addMainCategories(MainCategory mainCategory);

}

package com.example.Starbucks.api.category.service;

import com.example.Starbucks.api.category.model.MainCategory;
import com.example.Starbucks.api.category.dto.ResponseMainCategory;

import java.util.List;

public interface MainCategoryService {
    List<ResponseMainCategory.Side> getAllSideMainCategories();
    List<ResponseMainCategory> getAllMainCategories();
    MainCategory addMainCategories(MainCategory mainCategory);

}

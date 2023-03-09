package com.example.Starbucks.category.service;

import com.example.Starbucks.category.model.MainCategory;
import com.example.Starbucks.category.vo.ResponseMainCategory;

import java.util.List;

public interface MainCategoryService {
    public List<ResponseMainCategory> getAllMainCategories();
    public MainCategory addMainCategories(MainCategory mainCategory);

}

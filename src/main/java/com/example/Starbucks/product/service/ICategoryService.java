package com.example.Starbucks.product.service;

import com.example.Starbucks.product.model.Category;

import java.util.List;

public interface ICategoryService {

    void addCategory(Category category);
    Category getCategory(Integer categoryId);
    List<Category> getAll();
    List<Category> getAllType(String categoryType);
}

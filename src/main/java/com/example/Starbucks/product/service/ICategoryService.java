package com.example.Starbucks.product.service;

import com.example.Starbucks.product.model.Category;
import com.example.Starbucks.product.vo.RequestCategory;

import java.util.List;

public interface ICategoryService {

    void addCategory(RequestCategory requestCategory);
    Category getCategory(Integer categoryId);
    List<Category> getAll();
    List<Category> getAllType(String categoryType);
}

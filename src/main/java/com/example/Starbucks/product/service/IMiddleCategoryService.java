package com.example.Starbucks.product.service;

import com.example.Starbucks.product.model.MiddleCategory;

import java.util.List;

public interface IMiddleCategoryService {
    void addMiddleCategory(MiddleCategory middleCategory);
    MiddleCategory getMiddleCategory(Integer middleCategoryId);
    List<MiddleCategory> getAll();
    //List<MiddleCategory> getAllCategory(String category);
    void updateMiddleCategory(MiddleCategory middleCategory);
}

package com.example.Starbucks.category.service;

import com.example.Starbucks.category.model.MiddleCategory;
import com.example.Starbucks.category.vo.ResponseMiddleCategory;

import java.util.List;

public interface IMiddleCategoryService {
    void addMiddleCategory(MiddleCategory middleCategory);
    MiddleCategory getMiddleCategory(Integer middleCategoryId);
    List<MiddleCategory> getAll();
    //List<MiddleCategory> getAllCategory(String category);
    public List<ResponseMiddleCategory> getAllMiddleCategory();
    void updateMiddleCategory(MiddleCategory middleCategory);
}

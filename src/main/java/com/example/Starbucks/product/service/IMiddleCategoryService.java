package com.example.Starbucks.product.service;

import com.example.Starbucks.product.model.MiddleCategory;
import com.example.Starbucks.product.vo.ResponseMiddleCategory;

import java.util.List;

public interface IMiddleCategoryService {
    void addMiddleCategory(MiddleCategory middleCategory);
    MiddleCategory getMiddleCategory(Integer middleCategoryId);
    List<MiddleCategory> getAll();
    //List<MiddleCategory> getAllCategory(String category);
    public List<ResponseMiddleCategory> getAllMiddleCategory();
    void updateMiddleCategory(MiddleCategory middleCategory);
}

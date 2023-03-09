package com.example.Starbucks.category.service;

import com.example.Starbucks.category.model.MiddleCategory;
import com.example.Starbucks.category.vo.RequestMiddleCategory;
import com.example.Starbucks.category.vo.ResponseMiddleCategory;

import java.util.List;

public interface IMiddleCategoryService {
    void addMiddleCategory(RequestMiddleCategory requestMiddleCategory);
    public List<ResponseMiddleCategory> getAllMiddleCategory();
    void updateMiddleCategory(MiddleCategory middleCategory);
}

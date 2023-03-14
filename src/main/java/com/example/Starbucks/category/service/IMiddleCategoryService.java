package com.example.Starbucks.category.service;

import com.example.Starbucks.category.model.MiddleCategory;
import com.example.Starbucks.category.vo.RequestMiddleCategory;
import com.example.Starbucks.category.dto.ResponseMiddleCategory;

import java.util.List;

public interface IMiddleCategoryService {
    void addMiddleCategory(RequestMiddleCategory requestMiddleCategory);
    List<ResponseMiddleCategory> getAllMiddleCategory();
    void updateMiddleCategory(MiddleCategory middleCategory);
}

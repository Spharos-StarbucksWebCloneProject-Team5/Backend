package com.teamSiHyun.Starbucks.api.category.service;

import com.teamSiHyun.Starbucks.api.category.model.MiddleCategory;
import com.teamSiHyun.Starbucks.api.category.vo.RequestMiddleCategory;
import com.teamSiHyun.Starbucks.api.category.dto.ResponseMiddleCategory;

import java.util.List;

public interface IMiddleCategoryService {
    void addMiddleCategory(RequestMiddleCategory requestMiddleCategory);
    List<ResponseMiddleCategory> getAllMiddleCategory();
    void updateMiddleCategory(MiddleCategory middleCategory);
}

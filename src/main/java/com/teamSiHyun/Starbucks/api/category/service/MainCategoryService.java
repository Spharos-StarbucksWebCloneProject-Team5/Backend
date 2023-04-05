package com.teamSiHyun.Starbucks.api.category.service;

import com.teamSiHyun.Starbucks.api.category.model.MainCategory;
import com.teamSiHyun.Starbucks.api.category.dto.ResponseMainCategory;

import java.util.List;

public interface MainCategoryService {
    List<ResponseMainCategory.Side> getAllSideMainCategories();
    List<ResponseMainCategory> getAllMainCategories();
    MainCategory addMainCategories(MainCategory mainCategory);

}

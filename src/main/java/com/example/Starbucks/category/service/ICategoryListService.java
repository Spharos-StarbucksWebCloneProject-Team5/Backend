package com.example.Starbucks.category.service;

import com.example.Starbucks.category.dto.ResponsePage;
import org.springframework.data.domain.Pageable;

public interface ICategoryListService {

    ResponsePage searchByCategory(Integer mainCategoryId, Integer middleCategoryInteger, Integer pageNum, Pageable pageable);
    ResponsePage searchByNameOrDescription(String keyword, String keyword2, Integer pageNum, Pageable pageable);
}

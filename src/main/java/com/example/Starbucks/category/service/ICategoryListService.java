package com.example.Starbucks.category.service;

import com.example.Starbucks.category.dto.ResponsePage;
import com.example.Starbucks.category.dto.ResponseSearch;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICategoryListService {

    ResponsePage searchByCategory(Integer mainCategoryId, Integer middleCategoryInteger, Integer pageNum, Pageable pageable);
    ResponsePage searchByNameOrDescription(String keyword, String keyword2, Integer pageNum, Pageable pageable);
    List<Object> searchCache(String keyword);
}

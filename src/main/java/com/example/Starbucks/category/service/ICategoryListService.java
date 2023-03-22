package com.example.Starbucks.category.service;

import com.example.Starbucks.category.dto.ResponseSearch;
import com.example.Starbucks.category.model.CategoryList;
import com.example.Starbucks.category.vo.RequestCategoryList;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICategoryListService {

    List<Object> searchByCategory(Integer mainCategoryId, Integer middleCategoryInteger, Integer pageNum, Pageable pageable);
    List<Object> searchCache(String keyword, int pageNum, Pageable pageable);
    CategoryList addCategoryList(RequestCategoryList requestCategoryList);
}

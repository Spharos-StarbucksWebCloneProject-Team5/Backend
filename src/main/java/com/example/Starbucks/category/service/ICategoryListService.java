package com.example.Starbucks.category.service;

import com.example.Starbucks.category.dto.ResponsePage;
import com.example.Starbucks.category.dto.ResponseSearch;
import com.example.Starbucks.category.model.CategoryList;
import com.example.Starbucks.category.model.ProductList;
import com.example.Starbucks.category.vo.RequestCategoryList;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICategoryListService {

    ResponsePage searchByCategory(Integer mainCategoryId, Integer middleCategoryInteger, Integer pageNum, Pageable pageable);
    ResponsePage searchByNameOrDescription(String keyword, Integer pageNum, Pageable pageable);
    List<Object> searchCache(String keyword);
    List<ProductList> searchCache2(String keyword);
    CategoryList addCategoryList(RequestCategoryList requestCategoryList);
}

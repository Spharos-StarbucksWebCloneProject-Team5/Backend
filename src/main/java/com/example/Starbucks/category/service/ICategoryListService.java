package com.example.Starbucks.category.service;

import com.example.Starbucks.category.model.MainCategory;
import com.example.Starbucks.category.vo.RequestCategory;
import com.example.Starbucks.category.vo.ResponsePage;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICategoryListService {

    ResponsePage searchByCategory(Integer mainCategoryId, Integer middleCategoryInteger, Integer pageNum, Pageable pageable);

//    Page<ResponseCategoryList.categorySearchInfo> searchByNameOrDescription(String keyword, String keyword2, Integer pageNum, Pageable pageable);

    ResponsePage searchByNameOrDescription(String keyword, String keyword2, Integer pageNum, Pageable pageable);
}
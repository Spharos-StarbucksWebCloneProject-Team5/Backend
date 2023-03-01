package com.example.Starbucks.product.repository;

import com.example.Starbucks.product.model.CategoryList;
import com.example.Starbucks.product.vo.ResponseCategoryList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryListRepository extends JpaRepository<CategoryList, Long> {
    List<CategoryList> findAllByMainCategoryIdAndMiddleCategoryId
            (Integer mainCategoryId, Integer middleCategoryId);
}

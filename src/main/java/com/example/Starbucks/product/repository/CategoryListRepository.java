package com.example.Starbucks.product.repository;

import com.example.Starbucks.category.model.CategoryList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface CategoryListRepository extends JpaRepository<CategoryList, Long>, JpaSpecificationExecutor<CategoryList> {
    Page<CategoryList> findAllByMainCategoryIdAndMiddleCategoryId
            (Integer mainCategoryId, Integer middleCategoryId, Pageable pageable);
    Page<CategoryList> findByProductNameContainingOrProductDescriptionContaining(String keyword, String keyword2, Pageable pageable);
}

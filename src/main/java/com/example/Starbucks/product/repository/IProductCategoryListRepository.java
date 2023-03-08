package com.example.Starbucks.product.repository;

import com.example.Starbucks.category.model.CategoryList;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IProductCategoryListRepository extends JpaRepository<CategoryList,Long> {

    List<CategoryList> findAllByProductId(Long productId);

    List<CategoryList> findAllById(Integer categoryId);
    //List<ProductCategoryList> findAllByMiddleCategoryId(Integer middleCategoryId);
    List<CategoryList> findAll(Specification<CategoryList> spec);

}

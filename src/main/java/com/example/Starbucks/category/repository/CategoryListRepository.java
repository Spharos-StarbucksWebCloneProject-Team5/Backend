package com.example.Starbucks.category.repository;

import com.example.Starbucks.category.model.CategoryList;
import com.example.Starbucks.category.projection.IProduct;
import com.example.Starbucks.product.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface CategoryListRepository extends JpaRepository<CategoryList, Long>, JpaSpecificationExecutor<CategoryList> {
    Page<CategoryList> findAllByMainCategoryIdAndMiddleCategoryId
            (Integer mainCategoryId, Integer middleCategoryId, Pageable pageable);
    Page<CategoryList> findAllByMainCategoryId(Integer mainCategoryId, Pageable pageable);
    Page<CategoryList> findByProductNameContainingOrProductDescriptionContaining(String keyword, String keyword2, Pageable pageable);

    @Query(value = "select p.id, p.name, p.price, p.thumbnail from Product p left join CategoryList c")
//            "where p.name like %:keyword%")
    List<IProduct> findByProductNameContainingOrProductDescriptionContaining(@Param("keyword") String keyword);
}

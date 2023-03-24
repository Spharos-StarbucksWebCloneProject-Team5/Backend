package com.example.Starbucks.category.repository;

import com.example.Starbucks.category.model.CategoryList;
import com.example.Starbucks.category.projection.IProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface CategoryListRepository extends JpaRepository<CategoryList, Long>, JpaSpecificationExecutor<CategoryList> {
    @Query(value = "select p.id, p.name, p.price, p.thumbnail\n" +
            "from product p\n" +
            "left outer join\n" +
            "category_list c\n" +
            "on p.id = c.id\n" +
            "where main_category_id = :mainCategoryId and middle_category_id = :middleCategoryId"
            , countQuery = "select count(*) from product"
            , nativeQuery = true)
    Page<IProduct> searchByCategories
            (@Param("mainCategoryId") Integer mainCategoryId, @Param("middleCategoryId") Integer middleCategoryId, Pageable pageable);

    @Query(value = "select p.id, p.name, p.price, p.thumbnail\n" +
            "from product p\n" +
            "left outer join\n" +
            "category_list c\n" +
            "on p.id = c.id\n" +
            "where main_category_id = :mainCategoryId"
            , countQuery = "select count(*) from product"
            , nativeQuery = true)
    Page<IProduct> searchByMainCategory(@Param("mainCategoryId") Integer mainCategoryId, Pageable pageable);

    @Query(value = "SELECT p.id, p.name, p.price, p.thumbnail FROM product p left outer join category_list c on p.id = c.product_id \n" +
            "where p.name like %:keyword%"
            , countQuery = "SELECT COUNT(*) FROM product"
            , nativeQuery = true)
    Page<IProduct> searchKeyword(@Param("keyword") String keyword, Pageable pageable);
}

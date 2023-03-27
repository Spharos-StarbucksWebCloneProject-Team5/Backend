package com.example.Starbucks.category.repository;

import com.example.Starbucks.category.model.CategoryList;
import com.example.Starbucks.category.projection.IProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface CategoryListRepository extends JpaRepository<CategoryList, Long>, JpaSpecificationExecutor<CategoryList> {
    @Query(value = "select p.id, p.name, p.price, p.thumbnail\n" +
            "from product p\n" +
            "left outer join\n" +
            "category_list c\n" +
            "on p.id = c.id\n" +
            "where main_category_id = :mainCategoryId and middle_category_id = :middleCategoryId"
            , countQuery = "select count(*) from category_list c where main_category_id = :mainCategoryId and middle_category_id = :middleCategoryId"
            , nativeQuery = true)
    Page<IProduct> searchByCategories
            (@Param("mainCategoryId") Integer mainCategoryId, @Param("middleCategoryId") Integer middleCategoryId, Pageable pageable);

    @Query(value = "select p.id, p.name, p.price, p.thumbnail\n" +
            "from product p\n" +
            "left outer join\n" +
            "category_list c\n" +
            "on p.id = c.id\n" +
            "where main_category_id = :mainCategoryId"
            , countQuery = "select count(*) from category_list c where main_category_id = :mainCategoryId"
            , nativeQuery = true)
    Page<IProduct> searchByMainCategory(@Param("mainCategoryId") Integer mainCategoryId, Pageable pageable);

    @Query(value = "select p.id, p.name, p.price, p.thumbnail " +
            "from product p " +
            "where p.name like %:keyword% " +
            "and p.id > :productId " +
            "limit 8"
            , nativeQuery = true)
    List<IProduct> searchKeyword2(@Param("keyword") String keyword, @Param("productId") Long productId);

    @Query(value = "SELECT p.id, p.name, p.price, p.thumbnail, p.description FROM product p \n" +
            "where p.name like %:keyword%"
            , countQuery = "SELECT COUNT(p.id) from product p " +
            "where p.name like %:keyword% and p.description like %:keyword%"
            , nativeQuery = true)
    Page<IProduct> searchKeyword(@Param("keyword") String keyword, Pageable pageable);

    CategoryList findByProductId(Long productId);
}

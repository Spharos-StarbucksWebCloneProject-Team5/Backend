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
    Page<CategoryList> findAllByMainCategoryIdAndMiddleCategoryId
            (Integer mainCategoryId, Integer middleCategoryId, Pageable pageable);
    Page<CategoryList> findAllByMainCategoryId(Integer mainCategoryId, Pageable pageable);
    @Query(value = "SELECT p.id, p.name, p.price, p.thumbnail FROM product p left outer join category_list c on p.id = c.product_id \n" +
            "where p.name like %:keyword%"
            ,countQuery = "SELECT COUNT(*) FROM product"
            ,nativeQuery = true)
    Page<IProduct> findByProductNameContainingOrProductDescriptionContaining(@Param("keyword") String keyword, Pageable pageable);

    @Query(value = "SELECT p.id, p.name, p.price, p.thumbnail FROM product p left outer join category_list c on p.id = c.product_id \n" +
            "where p.name like %:keyword%"
            ,nativeQuery = true)
//    @Query(value = "select p.id, p.name, p.price, p.thumbnail from Product p join fetch CategoryList c")
    List<IProduct> searchKeyword(@Param("keyword") String keyword);
}

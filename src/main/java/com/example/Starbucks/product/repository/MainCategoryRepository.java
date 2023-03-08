package com.example.Starbucks.product.repository;

import com.example.Starbucks.category.model.MainCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MainCategoryRepository extends JpaRepository<MainCategory,Integer> {

    /*List<Category> findAllByType(String type);

    @Query(value = "select type from category group by type",nativeQuery = true)
    List<String> groupByType();*/
}

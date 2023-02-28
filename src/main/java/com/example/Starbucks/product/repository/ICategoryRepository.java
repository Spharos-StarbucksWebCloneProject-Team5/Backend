package com.example.Starbucks.product.repository;

import com.example.Starbucks.product.dto.CategoryDto;
import com.example.Starbucks.product.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ICategoryRepository extends JpaRepository<Category,Integer> {

    /*List<Category> findAllByType(String type);

    @Query(value = "select type from category group by type",nativeQuery = true)
    List<String> groupByType();*/
}

package com.example.Starbucks.product.repository;

import com.example.Starbucks.category.model.MiddleCategory;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IMiddleCategoryRepository  extends JpaRepository<MiddleCategory,Integer>{
    //List<MiddleCategory> findAllByCategory(String category);
}

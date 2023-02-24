package com.example.Starbucks.product.repository;

import com.example.Starbucks.product.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICategoryRepository extends JpaRepository<Category,Integer> {

    List<Category> findAllByType(String type);
}

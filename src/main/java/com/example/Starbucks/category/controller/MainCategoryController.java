package com.example.Starbucks.category.controller;

import com.example.Starbucks.category.model.MainCategory;
import com.example.Starbucks.category.service.MainCategoryService;
import com.example.Starbucks.category.vo.ResponseMainCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/categories/main")
public class MainCategoryController {
    private final MainCategoryService mainCategoryService;

    @GetMapping("")
    public ResponseEntity<List<ResponseMainCategory>> getAllMainCategories() {
        return ResponseEntity.ok(mainCategoryService.getAllMainCategories());
    }

    @PostMapping
    public ResponseEntity<MainCategory> addMainCategory(@RequestBody MainCategory mainCategory) {
        return ResponseEntity.ok(mainCategoryService.addMainCategories(mainCategory));
    }
}

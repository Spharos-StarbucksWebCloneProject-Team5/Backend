package com.example.Starbucks.category.controller;

import com.example.Starbucks.category.model.MainCategory;
import com.example.Starbucks.category.service.MainCategoryService;
import com.example.Starbucks.category.dto.ResponseMainCategory;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "카테고리")
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/categories/main")
public class MainCategoryController {
    private final MainCategoryService mainCategoryService;

    @GetMapping
    public ResponseEntity<List<ResponseMainCategory>> getAllMainCategories() {
        return ResponseEntity.ok(mainCategoryService.getAllMainCategories());
    }

    @PostMapping
    public ResponseEntity<MainCategory> addMainCategory(@RequestBody MainCategory mainCategory) {
        return ResponseEntity.ok(mainCategoryService.addMainCategories(mainCategory));
    }
}

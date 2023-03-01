package com.example.Starbucks.product.controller;

import com.example.Starbucks.product.service.MainCategoryService;
import com.example.Starbucks.product.vo.ResponseMainCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

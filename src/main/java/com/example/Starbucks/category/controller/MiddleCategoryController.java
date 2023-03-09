package com.example.Starbucks.category.controller;

import com.example.Starbucks.category.model.MiddleCategory;
import com.example.Starbucks.category.service.IMiddleCategoryService;
import com.example.Starbucks.category.vo.RequestMiddleCategory;
import com.example.Starbucks.category.vo.ResponseMiddleCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/categories/middle")
@RequiredArgsConstructor
public class MiddleCategoryController {

    private final IMiddleCategoryService iMiddleCategoryService;

    @PostMapping("/add")
    public void addMiddleCategory(@RequestBody RequestMiddleCategory requestMiddleCategory){
        iMiddleCategoryService.addMiddleCategory(requestMiddleCategory);
    }

    @GetMapping
    public ResponseEntity<List<ResponseMiddleCategory>> getAllMiddleCategory(){
        return ResponseEntity.ok(iMiddleCategoryService.getAllMiddleCategory());
    }

    @PostMapping("/update")
    public void updateMiddleCategory(@RequestBody MiddleCategory middleCategory){
        iMiddleCategoryService.updateMiddleCategory(middleCategory);
    }
}

package com.example.Starbucks.product.controller;

import com.example.Starbucks.product.model.MiddleCategory;
import com.example.Starbucks.product.service.IMiddleCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/category/middle")
@RequiredArgsConstructor
public class MiddleCategoryController {

    private final IMiddleCategoryService iMiddleCategoryService;

    @PostMapping("/add")
    public void addMiddleCategory(@RequestBody MiddleCategory middleCategory){
        iMiddleCategoryService.addMiddleCategory(middleCategory);
    }

   @GetMapping("/get/{middleCategoryId}")
    public MiddleCategory getMiddleCategory(@PathVariable Integer middleCategoryId){
        return iMiddleCategoryService.getMiddleCategory(middleCategoryId);
    }

    @GetMapping("/get/all")
    public List<MiddleCategory> getAllMiddleCategory(){
        return iMiddleCategoryService.getAll();
    }

    @PostMapping("/update")
    public void updateMiddleCategory(@RequestBody MiddleCategory middleCategory){
        iMiddleCategoryService.updateMiddleCategory(middleCategory);
    }
}

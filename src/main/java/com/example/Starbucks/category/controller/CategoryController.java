package com.example.Starbucks.category.controller;

import com.example.Starbucks.category.model.MainCategory;
import com.example.Starbucks.category.service.ICategoryListService;
import com.example.Starbucks.category.vo.RequestCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/category")
@RequiredArgsConstructor
public class CategoryController {

    private final ICategoryListService iCategoryListService;

    @PostMapping("/add")
    public void addCategory(@RequestBody RequestCategory requestCategory){
        iCategoryListService.addCategory(requestCategory);
    }

    @GetMapping("/get/{categoryId}")
    public MainCategory getCategory(@PathVariable Integer categoryId){
        return iCategoryListService.getCategory(categoryId);
    }

    @GetMapping("/get/all")
    public List<MainCategory> getAllCategory(){
        return iCategoryListService.getAll();
    }

    /*    @GetMapping("/get/type/{type}")
        public List<CategoryTypeDto> getAllByType(@PathVariable String type){
            return iCategoryService.getAllByType(type);
        }

        @GetMapping("/typename")
        public List<String> getCategoryTypeNames(){
            return iCategoryService.getCategoryTypeNames();
        }
    */
    @PostMapping("/update")
    public void updateCategory(@RequestBody MainCategory mainCategory){
        iCategoryListService.updateCategory(mainCategory);
    }

}

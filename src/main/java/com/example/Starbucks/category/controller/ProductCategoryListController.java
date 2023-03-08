package com.example.Starbucks.category.controller;

import com.example.Starbucks.category.model.CategoryList;
import com.example.Starbucks.product.service.IProductCategoryListService;
import com.example.Starbucks.category.vo.RequestProductCategoryList;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/product-category")
@RequiredArgsConstructor
public class ProductCategoryListController {

    final IProductCategoryListService iProductCategoryListService;

    @PostMapping("/add")
    public void addProductCategoryList(@RequestBody RequestProductCategoryList requestProductCategoryList){
        iProductCategoryListService.addProductCategoryList(requestProductCategoryList);
    }


    @GetMapping("/get/{productId}")
    public List<CategoryList> getAllByProduct(@PathVariable Long productId){
        return iProductCategoryListService.getByProductId(productId);
    }

    /*@GetMapping("/get/{categoryId}&{middleCategoryId}&{price}&{season}")
    public List<ProductCategoryList> getByCategory(@PathVariable(required = false) Integer categoryId
            , @PathVariable(required = false) Integer middleCategoryId
            , @PathVariable(required = false) Integer price
            , @PathVariable(required = false) Integer season){
        *//*if(categoryId == null){
            return iProductCategoryListService.getAll();
        }
        else if(middleCategoryId != null) {//프론트에서 값 0으로 달라고 하기? 힘들면 포기..
            return iProductCategoryListService.getByMiddleCategoryId(categoryId,middleCategoryId);
        }else{
            return iProductCategoryListService.getByCategoryId(categoryId);
        }*//*

        return iProductCategoryListService.getCategory(categoryId,middleCategoryId,price,season);
    }*/

    @GetMapping("/get/{categoryId}&{middleCategoryId}")
    public List<CategoryList> getByCategory(@PathVariable(required = false) Integer categoryId
            , @PathVariable(required = false) Integer middleCategoryId) {
        /*if(categoryId == null){
            return iProductCategoryListService.getAll();
        }
        else if(middleCategoryId != null) {//프론트에서 값 0으로 달라고 하기? 힘들면 포기..
            return iProductCategoryListService.getByMiddleCategoryId(categoryId,middleCategoryId);
        }else{
            return iProductCategoryListService.getByCategoryId(categoryId);
        }*/

        return iProductCategoryListService.getCategory(categoryId, middleCategoryId);
    }

}

package com.example.Starbucks.product.controller;

import com.example.Starbucks.product.service.ICategoryListService;
import com.example.Starbucks.product.vo.ResponseCategoryList;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/categories/")
public class CategoryListController {

    private final ICategoryListService iCategoryListService;

    @GetMapping("{mainId}&{middleId}")
    public ResponseEntity<List<ResponseCategoryList.categorySearchInfo>> searchProductByCategories(@PathVariable Integer mainId, @PathVariable Integer middleId) {
        return ResponseEntity.ok(iCategoryListService.searchByCategory(mainId, middleId));
    }
}

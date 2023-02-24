package com.example.Starbucks.product.controller;

import com.example.Starbucks.product.model.ProductCategoryList;
import com.example.Starbucks.product.service.IProductCategoryListService;
import com.example.Starbucks.product.vo.RequestProductCategoryList;
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
    public List<ProductCategoryList> getAllByProduct(@PathVariable Long productId){
        return iProductCategoryListService.getByProductId(productId);
    }

}

package com.example.Starbucks.product.controller;

import com.example.Starbucks.product.model.ProductImageList;
import com.example.Starbucks.product.service.IProductImageListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/product-image")
@RequiredArgsConstructor
public class ProductImageListController {

    private final IProductImageListService iProductImageListService;

    @PostMapping("/add")
    public void addProductImage(@RequestBody ProductImageList productImageList){
        iProductImageListService.addProductImage(productImageList);
    }

    @GetMapping("/get/{productId}")
    public List<ProductImageList> getProductImage(@PathVariable Long productId){
        return iProductImageListService.getByProductId(productId);
    }

    /*@GetMapping("/get/all")
    public List<ProductImageList> getAllCategory(){
        return iProductImageListService.getAll();
    }*/

    @PostMapping("/update")
    public void updateProductImageList(@RequestBody ProductImageList productImageList){
        iProductImageListService.updateProductImageList(productImageList);
    }
}

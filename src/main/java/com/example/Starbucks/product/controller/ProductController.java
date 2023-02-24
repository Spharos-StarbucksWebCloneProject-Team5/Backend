package com.example.Starbucks.product.controller;

import com.example.Starbucks.product.model.Product;
import com.example.Starbucks.product.service.IProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/product")
@RequiredArgsConstructor
public class ProductController {
    private final IProductService iProductService;

    @PostMapping("/add")
    public void addProduct(@RequestBody Product product){
        iProductService.addProduct(product);
    }

    @GetMapping("get/{productId}")
    public Product getProduct(@PathVariable Long productId){
        return iProductService.getProduct(productId);
    }

    @GetMapping("get/all")
    public List<Product> getAllProduct(){
        return iProductService.getAllProduct();
    }

}

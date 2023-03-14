package com.example.Starbucks.product.controller;

import com.example.Starbucks.event.vo.RequestEvent;
import com.example.Starbucks.product.model.Product;
import com.example.Starbucks.product.service.IProductService;
import com.example.Starbucks.product.vo.RequestProduct;
import com.example.Starbucks.product.vo.ResponseProduct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final IProductService iProductService;

    @PostMapping("")
    public ResponseEntity<?> addProduct(@RequestBody RequestProduct requestProduct){
        iProductService.addProduct(requestProduct);
        return null;
    }

    @GetMapping("{productId}")
    public ResponseEntity<ResponseProduct> getProduct(@PathVariable Long productId){
        return ResponseEntity.ok(iProductService.getProduct(productId));
    }

    @GetMapping("all")
    public ResponseEntity<List<ResponseProduct>> getAllProduct(){
        return ResponseEntity.ok(iProductService.getAllProduct());
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody RequestProduct requestProduct){
        iProductService.updateProduct(id ,requestProduct);
        ResponseEntity.ok();
        return null;
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id){
        iProductService.deleteProduct(id);
        ResponseEntity.ok();
        return null;
    }

}

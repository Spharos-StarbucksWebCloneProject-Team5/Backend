package com.example.Starbucks.product.controller;

import com.example.Starbucks.event.model.Event;
import com.example.Starbucks.product.model.ProductImageList;
import com.example.Starbucks.product.service.IProductImageListService;
import com.example.Starbucks.product.vo.RequestProduct;
import com.example.Starbucks.product.vo.RequestProductImageList;
import com.example.Starbucks.product.vo.ResponseProductImageList;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/product-images")
@RequiredArgsConstructor
public class ProductImageListController {

    private final IProductImageListService iProductImageListService;

    @PostMapping("")
    public ResponseEntity<?> addProductImage(@RequestBody RequestProductImageList requestProductImageList){
        iProductImageListService.addProductImage(requestProductImageList);
        return null;
    }

    @GetMapping("/{productId}")
    public ResponseEntity<List<ResponseProductImageList>> getProductImage(@PathVariable Long productId){
        return ResponseEntity.ok(iProductImageListService.getByProductId(productId));
    }

    /*@GetMapping("/get/all")
    public List<ProductImageList> getAllCategory(){
        return iProductImageListService.getAll();
    }*/

    @PutMapping("{id}")
    public ResponseEntity<?> updateProductImageList(@PathVariable Long id, @RequestBody RequestProductImageList requestProductImage){
        iProductImageListService.updateProductImageList(id, requestProductImage);
        ResponseEntity.ok();
        return null;
    }

    @DeleteMapping ("{id}")
    public ResponseEntity<?> deleteProductImageList(@PathVariable Long id){
        iProductImageListService.deleteProductImageList(id);
        ResponseEntity.ok();
        return null;
    }

}

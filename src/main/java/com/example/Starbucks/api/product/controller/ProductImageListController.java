package com.example.Starbucks.api.product.controller;

import com.example.Starbucks.api.product.service.IProductImageListService;
import com.example.Starbucks.api.product.vo.RequestProductImageList;
import com.example.Starbucks.api.product.vo.ResponseProductImageList;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/product-images")
@RequiredArgsConstructor
public class ProductImageListController {

    private final IProductImageListService iProductImageListService;

    @Operation(summary = "상품 이미지 등록", description = "상품 이미지 데이터 등록", tags = {"관리자"})
    @PostMapping("")
    public ResponseEntity<?> addProductImage(@RequestBody RequestProductImageList requestProductImageList){
        iProductImageListService.addProductImage(requestProductImageList);
        return null;
    }

    @Operation(summary = "상품 이미지 조회", description = "상품 이미지 조회", tags = {"상품"})
    @GetMapping("/{productId}")
    public ResponseEntity<List<ResponseProductImageList>> getProductImage(@PathVariable Long productId){
        return ResponseEntity.ok(iProductImageListService.getByProductId(productId));
    }

    /*@GetMapping("/get/all")
    public List<ProductImageList> getAllCategory(){
        return iProductImageListService.getAll();
    }*/

    @Operation(summary = "상품 이미지 수정", description = "상품 이미지 데이터 수정", tags = {"관리자"})
    @PutMapping("{id}")
    public ResponseEntity<?> updateProductImageList(@PathVariable Long id, @RequestBody RequestProductImageList requestProductImage){
        iProductImageListService.updateProductImageList(id, requestProductImage);
        ResponseEntity.ok();
        return null;
    }

    @Operation(summary = "상품 이미지 삭제", description = "상품 이미지 데이터 삭제", tags = {"관리자"})
    @DeleteMapping ("{id}")
    public ResponseEntity<?> deleteProductImageList(@PathVariable Long id){
        iProductImageListService.deleteProductImageList(id);
        ResponseEntity.ok();
        return null;
    }

}

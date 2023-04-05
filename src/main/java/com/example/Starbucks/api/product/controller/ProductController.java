package com.example.Starbucks.api.product.controller;

import com.example.Starbucks.api.product.service.IProductService;
import com.example.Starbucks.api.product.vo.RequestProduct;
import com.example.Starbucks.api.product.vo.ResponseProduct;
import com.example.Starbucks.api.category.dto.ResponseSearch;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final IProductService iProductService;

    @Operation(summary = "상품 등록 요청", description = "상품 데이터 등록", tags = {"관리자"})
    @PostMapping("")
    public ResponseEntity<?> addProduct(@RequestBody RequestProduct requestProduct){
        iProductService.addProduct(requestProduct);
        return null;
    }

    @Operation(summary = "상품 조회 요청", description = "상품 데이터를 조회합니다.", tags = {"상품"})
    @GetMapping("{productId}")
    public ResponseEntity<ResponseProduct> getProduct(@PathVariable Long productId){
        return ResponseEntity.ok(iProductService.getProduct(productId));
    }

    @Operation(summary = "상품 전체 조회 요청", description = "마지막 상품 번호로 페이지 처리 인덱싱", tags = {"상품"})
    @GetMapping("/test/")
    public ResponseEntity<List<ResponseSearch>> getAllProduct2(@Param("productId") Long productId){
        return ResponseEntity.ok(iProductService.getAllProduct2(productId));
    }

    @Operation(summary = "상품 데이터 수정 요청", description = "요청한 상품 데이터 수정", tags = {"관리자"})
    @PutMapping("{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody RequestProduct requestProduct){
        iProductService.updateProduct(id ,requestProduct);
        ResponseEntity.ok();
        return null;
    }

    @Operation(summary = "상품 데이터 삭제 요청", description = "요청한 상품 데이터 삭제", tags = {"관리자"})
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id){
        iProductService.deleteProduct(id);
        ResponseEntity.ok();
        return null;
    }

}

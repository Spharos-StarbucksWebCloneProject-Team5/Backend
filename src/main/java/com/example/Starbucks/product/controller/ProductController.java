package com.example.Starbucks.product.controller;

import com.example.Starbucks.event.vo.RequestEvent;
import com.example.Starbucks.product.dto.ResponseProductList;
import com.example.Starbucks.product.model.Product;
import com.example.Starbucks.product.service.IProductService;
import com.example.Starbucks.product.vo.RequestProduct;
import com.example.Starbucks.product.vo.ResponseProduct;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
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

    @Operation(summary = "상품 전체 조회 요청", description = "상품 데이터 전체를 조회합니다.", tags = {"상품"})
    @GetMapping
    public ResponseEntity<List<ResponseProductList>> getAllProduct(@Param("pageNum") int pageNum, Pageable pageable){
        return ResponseEntity.ok(iProductService.getAllProduct(pageNum, pageable));
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

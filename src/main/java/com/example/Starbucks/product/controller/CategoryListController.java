package com.example.Starbucks.product.controller;

import com.example.Starbucks.product.service.ICategoryListService;
import com.example.Starbucks.product.vo.ResponsePage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/categories/")
public class CategoryListController {

    private final ICategoryListService iCategoryListService;

    @GetMapping("/category/{mainId}&{middleId}&{pageNum}")
    public ResponseEntity<ResponsePage> searchProductByCategories(@PathVariable Integer mainId, @PathVariable Integer middleId,  @PathVariable Integer pageNum, @PageableDefault (page=0, size=10, sort="name", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(iCategoryListService.searchByCategory(mainId, middleId, pageNum, pageable));
    }

//    @GetMapping("/search/{keyword}&{pageNum}")
//    public ResponseEntity<Page<ResponseCategoryList.categorySearchInfo>> searchProductByKeyword
//            (@PathVariable String keyword, @PathVariable Integer pageNum, @PageableDefault (page=0, size=3, sort="productName", direction = Sort.Direction.DESC) Pageable pageable) {
//        return ResponseEntity.ok(iCategoryListService.searchByNameOrDescription(keyword, keyword, pageNum, pageable));
//    }

    @GetMapping("/search/{keyword}&{pageNum}")
    public ResponseEntity<ResponsePage> searchProductByKeyword
            (@PathVariable String keyword, @PathVariable Integer pageNum, @PageableDefault (page=0, size=5, sort="productName", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(iCategoryListService.searchByNameOrDescription(keyword, keyword, pageNum, pageable));
    }
}

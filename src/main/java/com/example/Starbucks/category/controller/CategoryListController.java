package com.example.Starbucks.category.controller;

import com.example.Starbucks.category.dto.ResponseSearch;
import com.example.Starbucks.category.model.CategoryList;
import com.example.Starbucks.category.model.ProductList;
import com.example.Starbucks.category.service.ICategoryListService;
import com.example.Starbucks.category.dto.ResponsePage;
import com.example.Starbucks.category.vo.RequestCategoryList;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "카테고리")
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/categories/")
public class CategoryListController {

    private final ICategoryListService iCategoryListService;

    @GetMapping("/category/{mainId}&{middleId}/{pageNum}") // mainCategory 만 선택된 경우, middleId를 0으로 보낸다.
    public ResponseEntity<ResponsePage> searchProductByCategories(@Param("mainId") Integer mainId, @Param("middleId") Integer middleId,  @Param("pageNum") Integer pageNum, @PageableDefault (page=0, size=10, sort="name", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(iCategoryListService.searchByCategory(mainId, middleId, pageNum, pageable));
    }

    @GetMapping("/search")
    public ResponseEntity<ResponsePage> searchProductByKeyword
            (@Param("keyword") String keyword, @Param("pageNum") Integer pageNum, @PageableDefault (page=0, size=5, direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(iCategoryListService.searchByNameOrDescription(keyword, pageNum, pageable));
    }

    @GetMapping("/testSearch")
    public ResponseEntity<List<Object>> testCache
            (@Param("keyword") String keyword) {
        return ResponseEntity.ok(iCategoryListService.searchCache(keyword));
    }

    @GetMapping("/testSearch2")
    public ResponseEntity<List<ProductList>> testCache2
            (@Param("keyword") String keyword) {
        return ResponseEntity.ok(iCategoryListService.searchCache2(keyword));
    }

    @PostMapping("/add")
    public ResponseEntity<CategoryList> addCategoryList(@RequestBody RequestCategoryList requestCategoryList) {
        return ResponseEntity.ok(iCategoryListService.addCategoryList(requestCategoryList));
    }
}

package com.example.Starbucks.category.controller;

import com.example.Starbucks.category.dto.ResponseSearch;
import com.example.Starbucks.category.model.CategoryList;
import com.example.Starbucks.category.model.ProductList;
import com.example.Starbucks.category.service.ICategoryListService;
import com.example.Starbucks.category.dto.ResponsePage;
import com.example.Starbucks.category.vo.RequestCategoryList;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/categories/")
public class CategoryListController {

    private final ICategoryListService iCategoryListService;

    @Operation(summary = "카테고리 검색", description = "mainCategoryId, middleCategoryId에 따른 카테고리 검색, mainCategory만 선택된 경우 middle은 0으로", tags = { "검색" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(schema = @Schema(implementation = ResponsePage.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @GetMapping("/category") // mainCategory 만 선택된 경우, middleId를 0으로 보낸다.
    public ResponseEntity<ResponsePage> searchProductByCategories(@Param("main") Integer main, @Param("middle") Integer middle, @Param("pageNum") Integer pageNum, @PageableDefault (page=0, size=10, sort="name", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(iCategoryListService.searchByCategory(main, middle, pageNum, pageable));
    }

    @Operation(summary = "키워드 검색", description = "keyword에 따른 상품 검색 결과", tags = { "검색" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(schema = @Schema(implementation = ResponsePage.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @GetMapping("/search")
    public ResponseEntity<ResponsePage> searchProductByKeyword
            (@Param("keyword") String keyword, @Param("pageNum") Integer pageNum, @PageableDefault (page=0, size=5, direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(iCategoryListService.searchByNameOrDescription(keyword, pageNum, pageable));
    }


    @Operation(summary = "키워드 검색 테스트", description = "캐시 저장하여 keyword 검색 결과 가져오기(JPQL 사용)", tags = { "검색" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(schema = @Schema(implementation = ResponseSearch.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @GetMapping("/testSearch")
    public ResponseEntity<List<Object>> testCache
            (@Param("keyword") String keyword, Pageable pageable) {
        return ResponseEntity.ok(iCategoryListService.searchCache(keyword, pageable));
    }

    @Operation(summary = "키워드 검색 테스트", description = "CRUD Repository 사용하여 캐시 저장 및 불러오기", tags = { "검색" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(schema = @Schema(implementation = ResponseSearch.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @GetMapping("/testSearch2")
    public ResponseEntity<List<ProductList>> testCache2
            (@Param("keyword") String keyword) {
        return ResponseEntity.ok(iCategoryListService.searchCache2(keyword));
    }

    @Operation(summary = "카테고리 리스트 추가", description = "mainCategoryId, middleCategoryId, productId에 따른 categoryList 추가", tags = { "관리자" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(schema = @Schema(implementation = CategoryList.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PostMapping("/add")
    public ResponseEntity<CategoryList> addCategoryList(@RequestBody RequestCategoryList requestCategoryList) {
        return ResponseEntity.ok(iCategoryListService.addCategoryList(requestCategoryList));
    }
}

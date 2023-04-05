package com.teamSiHyun.Starbucks.api.category.controller;

import com.teamSiHyun.Starbucks.api.category.dto.ResponsePage;
import com.teamSiHyun.Starbucks.api.category.dto.ResponseSearch;
import com.teamSiHyun.Starbucks.api.category.model.CategoryList;
import com.teamSiHyun.Starbucks.api.category.service.ICategoryListService;
import com.teamSiHyun.Starbucks.api.category.vo.RequestCategoryList;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "카테고리 검색", description = "대분류 이름, 중분류 아이디에 따른 검색", tags = {"검색"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(schema = @Schema(implementation = ResponseSearch.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @GetMapping
    public ResponseEntity<ResponsePage> searchByCategories
            (@Param("category") Integer category,
             @Param("subCategory") Integer subCategory,
             @Param("pageNum") Integer pageNum,
             @PageableDefault(page = 1, size = 8, sort = "name", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(iCategoryListService.searchByCategory(category, subCategory, pageNum, pageable));
    }

    @Operation(summary = "키워드 검색", description = "캐시 저장하여 keyword 검색 결과 가져오기(JPQL 사용)", tags = {"검색"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(schema = @Schema(implementation = ResponseSearch.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @GetMapping("/event")
    public ResponseEntity<ResponsePage> searchKeyword
            (@Param("keyword") String keyword, @Param("pageNum") int pageNum, @PageableDefault(page = 1, size = 8, direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(iCategoryListService.searchCache(keyword, pageNum, pageable));
    }

    @Operation(summary = "pageable 없이 키워드 검색", description = "pageable 없이 lastProductId를 받아서 인덱싱 처리 하여 불러오기", tags = {"검색"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(schema = @Schema(implementation = ResponseSearch.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @GetMapping("/event2")
    public ResponseEntity<List<ResponseSearch>> searchKeyword2
            (@Param("keyword") String keyword, @Param("lastProduct") Long lastProduct) {
        return ResponseEntity.ok(iCategoryListService.searchCache2(keyword, lastProduct));
    }

    @Operation(summary = "카테고리 리스트 추가", description = "mainCategoryId, middleCategoryId, productId에 따른 categoryList 추가", tags = {"관리자"})
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

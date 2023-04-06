package com.teamSiHyun.Starbucks.api.category.controller;

import com.teamSiHyun.Starbucks.api.category.model.MainCategory;
import com.teamSiHyun.Starbucks.api.category.service.MainCategoryService;
import com.teamSiHyun.Starbucks.api.category.dto.ResponseMainCategory;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/categories/main")
public class MainCategoryController {
    private final MainCategoryService mainCategoryService;

    @Operation(summary = "메인 카테고리 All 사이드바", description = "사이드바에 필요한 모든 메인 카테고리 가져오기", tags = { "카테고리" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(schema = @Schema(implementation = ResponseMainCategory.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @GetMapping("/side")
    public ResponseEntity<List<ResponseMainCategory.Side>> getAllSideMainCategories() {
        return ResponseEntity.ok(mainCategoryService.getAllSideMainCategories());
    }

    @Operation(summary = "메인 카테고리 All", description = "모든 메인 카테고리 가져오기", tags = { "카테고리" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(schema = @Schema(implementation = ResponseMainCategory.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @GetMapping
    public ResponseEntity<List<ResponseMainCategory>> getAllMainCategories() {
        return ResponseEntity.ok(mainCategoryService.getAllMainCategories());
    }

    @Operation(summary = "메인 카테고리 추가", description = "카테고리 추가하기", tags = { "관리자" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(schema = @Schema(implementation = MainCategory.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PostMapping
    public ResponseEntity<MainCategory> addMainCategory(@RequestBody MainCategory mainCategory) {
        return ResponseEntity.ok(mainCategoryService.addMainCategories(mainCategory));
    }
}

package com.example.Starbucks.api.category.controller;

import com.example.Starbucks.api.category.model.MiddleCategory;
import com.example.Starbucks.api.category.service.IMiddleCategoryService;
import com.example.Starbucks.api.category.vo.RequestMiddleCategory;
import com.example.Starbucks.api.category.dto.ResponseMiddleCategory;
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
@RequestMapping("/v1/api/categories/middle")
@RequiredArgsConstructor
public class MiddleCategoryController {

    private final IMiddleCategoryService iMiddleCategoryService;

    @Operation(summary = "미들 카테고리 추가", description = "미들 카테고리 추가하기", tags = { "관리자" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD  REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PostMapping("/add")
    public void addMiddleCategory(@RequestBody RequestMiddleCategory requestMiddleCategory){
        iMiddleCategoryService.addMiddleCategory(requestMiddleCategory);
    }

    @Operation(summary = "미들 카테고리 가져오기 All", description = "모든 미들 카테고리 가져오기", tags = { "카테고리" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(schema = @Schema(implementation = ResponseMiddleCategory.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @GetMapping
    public ResponseEntity<List<ResponseMiddleCategory>> getAllMiddleCategory(){
        return ResponseEntity.ok(iMiddleCategoryService.getAllMiddleCategory());
    }

    @Operation(summary = "미들 카테고리 수정", description = "미들 카테고리 수정하기", tags = { "관리자" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(schema = @Schema(implementation = MiddleCategory.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PostMapping("/update")
    public void updateMiddleCategory(@RequestBody MiddleCategory middleCategory){
        iMiddleCategoryService.updateMiddleCategory(middleCategory);
    }
}

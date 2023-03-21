package com.example.Starbucks.category.controller;

import com.example.Starbucks.category.dto.ResponsePage;
import com.example.Starbucks.category.model.MiddleCategory;
import com.example.Starbucks.category.service.IMiddleCategoryService;
import com.example.Starbucks.category.vo.RequestMiddleCategory;
import com.example.Starbucks.category.dto.ResponseMiddleCategory;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/categories/middle")
@RequiredArgsConstructor
public class MiddleCategoryController {

    private final IMiddleCategoryService iMiddleCategoryService;

    @Operation(summary = "미들 카테고리 추가", description = "미들 카테고리 추가하기", tags = { "admin" })
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

    @Operation(summary = "미들 카테고리 수정", description = "미들 카테고리 수정하기", tags = { "admin" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(schema = @Schema(implementation = ResponsePage.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PostMapping("/update")
    public void updateMiddleCategory(@RequestBody MiddleCategory middleCategory){
        iMiddleCategoryService.updateMiddleCategory(middleCategory);
    }
}

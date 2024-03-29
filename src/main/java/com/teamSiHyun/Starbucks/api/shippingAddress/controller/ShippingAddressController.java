package com.teamSiHyun.Starbucks.api.shippingAddress.controller;

import com.teamSiHyun.Starbucks.api.shippingAddress.service.IShippingAddressService;
import com.teamSiHyun.Starbucks.api.shippingAddress.vo.RequestShippingAddress;
import com.teamSiHyun.Starbucks.api.shippingAddress.vo.ResponseShippingAddress;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/shippingAddress")
@RequiredArgsConstructor
public class ShippingAddressController {
    private final IShippingAddressService iShippingAddressService;

    @Operation(summary = "배송지 등록 요청", description = "배송지 정보가 등록 됩니다.", tags = { "배송지" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
    })
    @PostMapping("")
    public ResponseEntity<?> addShippingAddress(Authentication authentication, @RequestBody RequestShippingAddress requestShippingAddress){
        iShippingAddressService.addShippingAddress(authentication,requestShippingAddress);
        ResponseEntity.ok();
        return null;
    }

    @Operation(summary = "배송지 정보 요청", description = "요청하는 ID의 배송지 정보를 가져옵니다.", tags = { "배송지" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
    })
    @GetMapping("/{shippingAddressId}")
    public ResponseEntity<ResponseShippingAddress> getShippingAddress(@PathVariable Long shippingAddressId){
        return ResponseEntity.ok(iShippingAddressService.getShippingAddress(shippingAddressId));
    }

    @Operation(summary = "유저의 모든 배송지 정보 요청", description = "모든 배송지 정보를 가져옵니다.", tags = { "배송지" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
    })
    @GetMapping("")
    public ResponseEntity<List<ResponseShippingAddress>> getAllShippingAddress(Authentication authentication){
        return ResponseEntity.ok(iShippingAddressService.getAllShippingAddress(authentication));
    }

    @Operation(summary = "배송지 수정 요청", description = "배송지 정보를 수정합니다.", tags = { "배송지" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
    })
    @PutMapping("{shippingAddressId}")
    public ResponseEntity<?> updateShippingAddress(@PathVariable Long shippingAddressId, @RequestBody RequestShippingAddress requestShippingAddress){
        iShippingAddressService.updateShippingAddress(shippingAddressId, requestShippingAddress);
        ResponseEntity.ok();
        return null;
    }

    @Operation(summary = "배송지 삭제 요청", description = "배송지 정보를 삭제합니다.", tags = { "배송지" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
    })
    @DeleteMapping("{shippingAddressId}")
    public ResponseEntity<?> deleteShippingAddress(@PathVariable Long shippingAddressId){
        iShippingAddressService.deleteShippingAddress(shippingAddressId);
        ResponseEntity.ok();
        return null;
    }

    @Operation(summary = "메인배송지 요청", description = "기본배송지를 가져옵니다.", tags = { "배송지" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
    })
    @GetMapping("/main")
    public ResponseEntity<ResponseShippingAddress> getMainShippingAddress(Authentication authentication){
        return ResponseEntity.ok(iShippingAddressService.getMainShippingAddress(authentication));
    }
}
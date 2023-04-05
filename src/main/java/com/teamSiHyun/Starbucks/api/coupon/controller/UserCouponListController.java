package com.teamSiHyun.Starbucks.api.coupon.controller;

import com.teamSiHyun.Starbucks.api.coupon.dto.UserCouponDto;
import com.teamSiHyun.Starbucks.api.coupon.model.Coupon;
import com.teamSiHyun.Starbucks.api.coupon.service.IUserCouponListService;
import com.teamSiHyun.Starbucks.api.coupon.vo.RequestUserCouponList;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/user-coupons")
@RequiredArgsConstructor
public class UserCouponListController{

    final IUserCouponListService iUserCouponListService;

    @Operation(summary = "유저 쿠폰 등록", description = "유저 쿠폰 등록", tags = {"쿠폰"})
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = Coupon.class)))
    @PostMapping("")
    public ResponseEntity<?> addUserCouponList(@RequestBody RequestUserCouponList requestUserCouponList){
        iUserCouponListService.addUserCouponList(requestUserCouponList);
        return null;
    }

    @Operation(summary = "유저 쿠폰목록 조회", description = "유저 쿠폰목록 조회", tags = {"쿠폰"})
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = UserCouponDto.class)))
    @GetMapping("/{userId}")
    public ResponseEntity<List<UserCouponDto>> getAllByUser(@PathVariable Long userId){
        return ResponseEntity.ok(iUserCouponListService.getByUserId(userId));
    }

}
package com.teamSiHyun.Starbucks.api.coupon.controller;

import com.teamSiHyun.Starbucks.api.coupon.model.Coupon;
import com.teamSiHyun.Starbucks.api.coupon.service.ICouponService;
import com.teamSiHyun.Starbucks.api.coupon.vo.RequestCreateCoupon;
import com.teamSiHyun.Starbucks.api.coupon.vo.RequestUpdateCoupon;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/coupons")
@RequiredArgsConstructor
public class CouponController {

    private final ICouponService iCouponService;

    @Operation(summary = "신규 쿠폰 등록", description = "신규 쿠폰 등록", tags = {"관리자"})
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = Coupon.class)))
    @PostMapping("")
    public @ResponseBody ResponseEntity<Void> addCoupon(@RequestBody RequestCreateCoupon requestCreateCoupon){
        iCouponService.addCoupon(requestCreateCoupon);
        return ResponseEntity.ok().build();
    }

//    @Operation(summary = "쿠폰 조회", description = "쿠폰 상세 내용 확인", tags = {"admin"})
//    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ResponseCoupon.class)))
//    @GetMapping("/{id}")
//    public @ResponseBody ResponseEntity<CouponDto> getCoupon(@PathVariable Long id){
//        return ResponseEntity.ok(iCouponService.getCoupon(id));
//    }

    @Operation(summary = "쿠폰 전체조회", description = "쿠폰 상세 내용 전체 조회", tags = {"쿠폰"})
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = Coupon.class)))
    @GetMapping("/all")
    public ResponseEntity<List<Coupon>> getAllCoupon(){
        return ResponseEntity.ok(iCouponService.getAllCoupon());
    }

    @Operation(summary = "쿠폰 상태 변경", description = "쿠폰 상세 내용 전체 조회", tags = {"쿠폰"})
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = Coupon.class)))
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCoupon(@PathVariable Long id , @RequestBody RequestUpdateCoupon requestUpdateCoupon){
        iCouponService.updateCoupon(id, requestUpdateCoupon);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "쿠폰 삭제", description = "쿠폰 삭제", tags = {"관리자"})
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = Coupon.class)))
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCoupon(@PathVariable Long id){
        iCouponService.deleteCoupon(id);
        return ResponseEntity.ok().build();
    }


}

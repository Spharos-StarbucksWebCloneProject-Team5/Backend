package com.example.Starbucks.coupon.controller;

import com.example.Starbucks.coupon.model.Coupon;
import com.example.Starbucks.coupon.service.ICouponService;
import com.example.Starbucks.coupon.vo.RequestCreateCoupon;
import com.example.Starbucks.coupon.vo.RequestUpdateCoupon;
import com.example.Starbucks.coupon.vo.ResponseCoupon;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/coupons")
@RequiredArgsConstructor
public class CouponController {

    private final ICouponService iCouponService;

    @PostMapping("")
    public @ResponseBody ResponseEntity<ResponseCoupon> addCoupon(@RequestBody RequestCreateCoupon requestCreateCoupon){
        return ResponseEntity.ok(iCouponService.addCoupon(requestCreateCoupon));
    }

    @GetMapping("/{id}")
    public @ResponseBody ResponseEntity<ResponseCoupon> getCoupon(@PathVariable Long id){
        return ResponseEntity.ok(iCouponService.getCoupon(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Coupon>> getAllCoupon(){
        return ResponseEntity.ok(iCouponService.getAllCoupon());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCoupon(@PathVariable Long id , @RequestBody RequestUpdateCoupon requestUpdateCoupon){
        iCouponService.updateCoupon(id, requestUpdateCoupon);
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCoupon(@PathVariable Long id){
        iCouponService.deleteCoupon(id);
        return null;
    }


}

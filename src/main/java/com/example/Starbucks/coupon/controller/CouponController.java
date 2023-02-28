package com.example.Starbucks.coupon.controller;

import com.example.Starbucks.coupon.model.Coupon;
import com.example.Starbucks.coupon.service.ICouponService;
import com.example.Starbucks.coupon.vo.RequestCoupon;
import com.example.Starbucks.product.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/coupon")
@RequiredArgsConstructor
public class CouponController {

    private final ICouponService iCouponService;

    @PostMapping("/add")
    public void addCoupon(@RequestBody Coupon coupon){
        iCouponService.addCoupon(coupon);
    }

    @GetMapping("/get/{couponId}")
    public Coupon getCoupon(@PathVariable Long couponId){
        return iCouponService.getCoupon(couponId);
    }

    @GetMapping("/get/all")
    public List<Coupon> getAllCoupon(){
        return iCouponService.getAllCoupon();
    }


}

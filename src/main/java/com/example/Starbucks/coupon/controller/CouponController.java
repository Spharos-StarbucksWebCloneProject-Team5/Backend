package com.example.Starbucks.coupon.controller;

import com.example.Starbucks.coupon.model.Coupon;
import com.example.Starbucks.coupon.service.ICouponService;
import com.example.Starbucks.coupon.vo.RequestCreateCoupon;
import com.example.Starbucks.coupon.vo.RequestUpdateCoupon;
import com.example.Starbucks.coupon.vo.ResponseCoupon;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/coupon")
@RequiredArgsConstructor
public class CouponController {

    private final ICouponService iCouponService;

    @PostMapping("")
    public @ResponseBody ResponseCoupon addCoupon(@RequestBody RequestCreateCoupon requestCreateCoupon){
        return iCouponService.addCoupon(requestCreateCoupon);
    }

    @GetMapping("/{id}")
    public @ResponseBody ResponseCoupon getCoupon(@PathVariable Long id){
        return iCouponService.getCoupon(id);
    }

    @GetMapping("/all")
    public List<Coupon> getAllCoupon(){
        return iCouponService.getAllCoupon();
    }

    @PutMapping("/{id}")
    public void updateCoupon(@PathVariable Long id , RequestUpdateCoupon requestUpdateCoupon){
        iCouponService.updateCoupon(id, requestUpdateCoupon);

    }


}

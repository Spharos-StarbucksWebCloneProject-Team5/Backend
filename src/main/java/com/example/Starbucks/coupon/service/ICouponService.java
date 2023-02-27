package com.example.Starbucks.coupon.service;

import com.example.Starbucks.coupon.model.Coupon;
import com.example.Starbucks.coupon.vo.RequestCoupon;

import java.util.List;

public interface ICouponService {

    void addCoupon(Coupon coupon);
    Coupon getCoupon(Long couponId);
    List<Coupon> getAllCoupon();


    //List<Category> getAllType(String categoryType);
}

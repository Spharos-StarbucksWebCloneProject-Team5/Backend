package com.example.Starbucks.coupon.service;

import com.example.Starbucks.coupon.model.Coupon;
import com.example.Starbucks.coupon.vo.RequestCreateCoupon;
import com.example.Starbucks.coupon.vo.RequestUpdateCoupon;

import java.util.List;

public interface ICouponService {

    void addCoupon(RequestCreateCoupon requestCreateCoupon);
//    CouponDto getCoupon(Long id);
    List<Coupon> getAllCoupon();

    void updateCoupon(Long id, RequestUpdateCoupon requestUpdateCoupon);

    void deleteCoupon(Long id);

}

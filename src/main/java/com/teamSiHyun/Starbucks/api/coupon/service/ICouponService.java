package com.teamSiHyun.Starbucks.api.coupon.service;

import com.teamSiHyun.Starbucks.api.coupon.model.Coupon;
import com.teamSiHyun.Starbucks.api.coupon.vo.RequestCreateCoupon;
import com.teamSiHyun.Starbucks.api.coupon.vo.RequestUpdateCoupon;

import java.util.List;

public interface ICouponService {

    void addCoupon(RequestCreateCoupon requestCreateCoupon);
//    CouponDto getCoupon(Long id);
    List<Coupon> getAllCoupon();

    void updateCoupon(Long id, RequestUpdateCoupon requestUpdateCoupon);

    void deleteCoupon(Long id);

}

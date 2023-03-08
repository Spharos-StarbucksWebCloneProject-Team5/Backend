package com.example.Starbucks.coupon.service;

import com.example.Starbucks.coupon.model.UserCouponList;
import com.example.Starbucks.coupon.vo.RequestUserCouponList;
import com.example.Starbucks.coupon.vo.ResponseUserCouponList;

import java.util.List;

public interface IUserCouponListService {

    void addUserCouponList(RequestUserCouponList requestUserCouponList);
    List<ResponseUserCouponList> getByUserId(Long userId);
}

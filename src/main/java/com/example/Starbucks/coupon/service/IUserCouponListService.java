package com.example.Starbucks.coupon.service;

import com.example.Starbucks.coupon.dto.UserCouponDto;
import com.example.Starbucks.coupon.vo.RequestUserCouponList;

import java.util.List;

public interface IUserCouponListService {

    void addUserCouponList(RequestUserCouponList requestUserCouponList);
    List<UserCouponDto> getByUserId(Long userId);
}

package com.example.Starbucks.api.coupon.service;

import com.example.Starbucks.api.coupon.dto.UserCouponDto;
import com.example.Starbucks.api.coupon.vo.RequestUserCouponList;

import java.util.List;

public interface IUserCouponListService {

    void addUserCouponList(RequestUserCouponList requestUserCouponList);
    List<UserCouponDto> getByUserId(Long userId);
}

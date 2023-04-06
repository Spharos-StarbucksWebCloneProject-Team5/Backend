package com.teamSiHyun.Starbucks.api.coupon.service;

import com.teamSiHyun.Starbucks.api.coupon.dto.UserCouponDto;
import com.teamSiHyun.Starbucks.api.coupon.vo.RequestUserCouponList;

import java.util.List;

public interface IUserCouponListService {

    void addUserCouponList(RequestUserCouponList requestUserCouponList);
    List<UserCouponDto> getByUserId(Long userId);
}

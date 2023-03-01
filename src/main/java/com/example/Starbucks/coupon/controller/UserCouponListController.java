package com.example.Starbucks.coupon.controller;

import com.example.Starbucks.coupon.model.UserCouponList;
import com.example.Starbucks.coupon.service.IUserCouponListService;
import com.example.Starbucks.coupon.vo.RequestUserCouponList;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/user-coupon")
@RequiredArgsConstructor
public class UserCouponListController{

    final IUserCouponListService iUserCouponListService;

    @PostMapping("")
    public void addUserCouponList(@RequestBody RequestUserCouponList requestUserCouponList){
        iUserCouponListService.addUserCouponList(requestUserCouponList);
    }

    @GetMapping("/{userId}")
    public List<UserCouponList> getAllByUser(@PathVariable Long userId){
        return iUserCouponListService.getByUserId(userId);
    }

}

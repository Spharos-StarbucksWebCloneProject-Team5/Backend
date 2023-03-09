package com.example.Starbucks.coupon.controller;

import com.example.Starbucks.coupon.model.UserCouponList;
import com.example.Starbucks.coupon.repository.IUserCouponListRepository;
import com.example.Starbucks.coupon.service.IUserCouponListService;
import com.example.Starbucks.coupon.vo.RequestUserCouponList;
import com.example.Starbucks.coupon.vo.ResponseUserCouponList;
import com.example.Starbucks.utility.BaseTimeEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/user-coupons")
@RequiredArgsConstructor
public class UserCouponListController{

    final IUserCouponListService iUserCouponListService;

    @PostMapping("")
    public ResponseEntity<?> addUserCouponList(@RequestBody RequestUserCouponList requestUserCouponList){
        iUserCouponListService.addUserCouponList(requestUserCouponList);
        return null;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<ResponseUserCouponList>> getAllByUser(@PathVariable Long userId){
        return ResponseEntity.ok(iUserCouponListService.getByUserId(userId));
    }

}
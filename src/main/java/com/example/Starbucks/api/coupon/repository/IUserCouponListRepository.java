package com.example.Starbucks.api.coupon.repository;

import com.example.Starbucks.api.coupon.model.UserCouponList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IUserCouponListRepository extends JpaRepository<UserCouponList,Long> {

    List<UserCouponList> findAllByUserId(Long userId);



}

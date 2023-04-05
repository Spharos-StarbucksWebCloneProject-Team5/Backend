package com.teamSiHyun.Starbucks.api.coupon.repository;

import com.teamSiHyun.Starbucks.api.coupon.model.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICouponRepository extends JpaRepository<Coupon,Long> {
}

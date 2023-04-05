package com.example.Starbucks.api.coupon.repository;

import com.example.Starbucks.api.coupon.model.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICouponRepository extends JpaRepository<Coupon,Long> {
}

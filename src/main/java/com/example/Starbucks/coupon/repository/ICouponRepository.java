package com.example.Starbucks.coupon.repository;

import com.example.Starbucks.coupon.model.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICouponRepository extends JpaRepository<Coupon,Long> {
}

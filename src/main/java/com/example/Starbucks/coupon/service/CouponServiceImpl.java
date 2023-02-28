package com.example.Starbucks.coupon.service;

import com.example.Starbucks.coupon.model.Coupon;
import com.example.Starbucks.coupon.repository.ICouponRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CouponServiceImpl implements ICouponService {

    private final ICouponRepository iCouponRepository;

    @Override
    public void addCoupon(Coupon coupon) {
        iCouponRepository.save(coupon);
    }

    @Override
    public Coupon getCoupon(Long couponId) {
        return iCouponRepository.findById(couponId).get();
    }

    @Override
    public List<Coupon> getAllCoupon() {
        return iCouponRepository.findAll();
    }
}

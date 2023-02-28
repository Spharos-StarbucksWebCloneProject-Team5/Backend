package com.example.Starbucks.coupon.service;

import com.example.Starbucks.coupon.model.Coupon;
import com.example.Starbucks.coupon.repository.ICouponRepository;
import com.example.Starbucks.coupon.vo.RequestCreateCoupon;
import com.example.Starbucks.coupon.vo.RequestUpdateCoupon;
import com.example.Starbucks.coupon.vo.ResponseCoupon;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CouponServiceImpl implements ICouponService {

    private final ICouponRepository iCouponRepository;

    @Override
    public ResponseCoupon addCoupon(RequestCreateCoupon requestCreateCoupon) {
        Coupon coupon = Coupon.builder()
                .name(requestCreateCoupon.getName())
                .discount(requestCreateCoupon.getDiscount())
                .end_date(requestCreateCoupon.getEnd_date())
                .status(requestCreateCoupon.getStatus())
                .type(requestCreateCoupon.getType())
                .build();
        iCouponRepository.save(coupon);
        ResponseCoupon responseCoupon = ResponseCoupon.builder()
                .name(requestCreateCoupon.getName())
                .discount(requestCreateCoupon.getDiscount())
                .end_date(requestCreateCoupon.getEnd_date())
                .status(requestCreateCoupon.getStatus())
                .type(requestCreateCoupon.getType())
                .build();
        return responseCoupon;
    }

    @Override
    public ResponseCoupon getCoupon(Long id) {
        Coupon coupon = iCouponRepository.findById(id).get();
        ResponseCoupon responseCoupon = ResponseCoupon.builder()
                .name(coupon.getName())
                .discount(coupon.getDiscount())
                .end_date(coupon.getEnd_date())
                .status(coupon.getStatus())
                .type(coupon.getType())
                .build();
        return responseCoupon;
    }

    @Override
    public List<Coupon> getAllCoupon() {
        return iCouponRepository.findAll();
    }

    @Override
    public void updateCoupon(Long id, RequestUpdateCoupon requestUpdateCoupon) {
        Coupon coupon = iCouponRepository.findById(id).get();
        coupon.setStatus(requestUpdateCoupon.getStatus());
        log.info(requestUpdateCoupon.toString());
        log.info(coupon.toString());
        iCouponRepository.save(coupon);
    }

    @Override
    public void deleteCoupon(Long id) {
        Coupon coupon = iCouponRepository.findById(id).get();
        iCouponRepository.delete(coupon);
    }


}

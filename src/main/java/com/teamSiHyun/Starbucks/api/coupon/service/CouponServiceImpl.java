package com.teamSiHyun.Starbucks.api.coupon.service;

import com.teamSiHyun.Starbucks.api.coupon.model.Coupon;
import com.teamSiHyun.Starbucks.api.coupon.vo.RequestCreateCoupon;
import com.teamSiHyun.Starbucks.api.coupon.vo.RequestUpdateCoupon;
import com.teamSiHyun.Starbucks.api.coupon.repository.ICouponRepository;
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
    public void addCoupon(RequestCreateCoupon requestCreateCoupon) {
        iCouponRepository.save(Coupon.builder()
                        .name(requestCreateCoupon.getName())
                        .discount(requestCreateCoupon.getDiscount())
                        .end_date(requestCreateCoupon.getEnd_date())
                        .status(requestCreateCoupon.getStatus())
                        .type(requestCreateCoupon.getType())
                        .build());
    }

//    @Override
//    public CouponDto getCoupon(Long id) {
//        Coupon coupon = iCouponRepository.findById(id).get();
//        CouponDto couponDto = CouponDto.builder()
//                .name(coupon.getName())
//                .discount(coupon.getDiscount())
//                .end_date(coupon.getEnd_date())
//                .status(coupon.getStatus())
//                .type(coupon.getType())
//                .build();
//        return couponDto;
//    }

    @Override
    public List<Coupon> getAllCoupon() {
        return iCouponRepository.findAll();
    }

    @Override
    public void updateCoupon(Long id, RequestUpdateCoupon requestUpdateCoupon) {
        Coupon coupon = iCouponRepository.findById(id).get();
        iCouponRepository.save(Coupon.builder()
                        .id(coupon.getId())
                        .name(coupon.getName())
                        .discount(coupon.getDiscount())
                        .end_date(coupon.getEnd_date())
                        .status(requestUpdateCoupon.getStatus())
                        .type(coupon.getType())
                        .build());
    }

    @Override
    public void deleteCoupon(Long id) {
        Coupon coupon = iCouponRepository.findById(id).get();
        iCouponRepository.delete(coupon);
    }


}

package com.example.Starbucks.api.coupon.service;

import com.example.Starbucks.api.coupon.dto.UserCouponDto;
import com.example.Starbucks.api.coupon.model.UserCouponList;
import com.example.Starbucks.api.coupon.vo.RequestUserCouponList;
import com.example.Starbucks.api.users.repository.UserRepository;
import com.example.Starbucks.api.coupon.repository.ICouponRepository;
import com.example.Starbucks.api.coupon.repository.IUserCouponListRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserCouponListServiceImpl implements IUserCouponListService {


    private final IUserCouponListRepository iUserCouponListRepository;
    private final UserRepository userRepository;
    private final ICouponRepository iCouponRepository;


    @Override
    public void addUserCouponList(RequestUserCouponList requestUserCouponList) {
        iUserCouponListRepository.save(UserCouponList.builder()
                .user(userRepository.findById(requestUserCouponList.getUserId()).get())
                .coupon(iCouponRepository.findById(requestUserCouponList.getCouponId()).get())
                .build()
        );

    }

    @Override
    public List<UserCouponDto> getByUserId(Long userId) {
        List<UserCouponDto> userCoupon = iUserCouponListRepository.findAllByUserId(userId).stream()
                .map(coupon -> UserCouponDto.builder()
                        .name(coupon.getCoupon().getName())
                        .discount(coupon.getCoupon().getDiscount())
                        .end_date(coupon.getCoupon().getEnd_date())
                        .status(coupon.getCoupon().getStatus())
                        .type(coupon.getCoupon().getType())
                        .build())
                .collect(Collectors.toList());

        return userCoupon;
    }

}
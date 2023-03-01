package com.example.Starbucks.coupon.service;

import com.example.Starbucks.coupon.model.UserCouponList;
import com.example.Starbucks.coupon.repository.ICouponRepository;
import com.example.Starbucks.coupon.repository.IUserCouponListRepository;
import com.example.Starbucks.coupon.vo.RequestUserCouponList;
import com.example.Starbucks.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<UserCouponList> getByUserId(Long userId) {
        return iUserCouponListRepository.findAllByUserId(userId);
    }

}

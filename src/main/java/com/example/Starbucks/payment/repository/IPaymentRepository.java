package com.example.Starbucks.payment.repository;

import com.example.Starbucks.payment.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface IPaymentRepository extends JpaRepository<Payment, Long> {
    //Payment findAllByProductId(Long productId);
    Payment findById(long id);
    List<Payment> findAllByUserId(Long userId);
    /*List<Payment> findByUserIdAndGift(Long userId,boolean isGift);
    List<Payment> findByUserIdAndShippingStatus(Long userId,Integer shipping_status);
    List<Payment> findByUserIdAndPayStatus(Long userId, Integer pay_status);
    List<Payment> findByUserIdAndPayStatusAndGift(Long userId, Integer pay_status, boolean isGift);*/
}

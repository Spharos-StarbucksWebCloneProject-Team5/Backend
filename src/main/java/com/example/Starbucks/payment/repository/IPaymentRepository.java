package com.example.Starbucks.payment.repository;

import com.example.Starbucks.payment.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface IPaymentRepository extends JpaRepository<Payment, Long> {
    Payment findById(long id);
    List<Payment> findAllByUserId(Long userId);
}

package com.example.Starbucks.payment.service;

import com.example.Starbucks.payment.vo.RequestPayment;
import com.example.Starbucks.payment.model.Payment;
import com.example.Starbucks.payment.vo.ResponsePayment;

import java.util.List;

public interface IPaymentService {
    ResponsePayment addPayment(RequestPayment requestPayment);
    //List<Payment> getByPaymentId(Long paymentId);
}

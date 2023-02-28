package com.example.Starbucks.payment.service;

import com.example.Starbucks.payment.vo.RequestPayment;
import com.example.Starbucks.payment.vo.RequestPaymentCancel;
import com.example.Starbucks.payment.vo.ResponsePayment;
import com.example.Starbucks.payment.vo.ResponsePaymentShipping;

public interface IPaymentService {
    ResponsePayment addPayment(RequestPayment requestPayment);
    //ResponseUser getUser(Long id);
    void cancelPayment(RequestPaymentCancel requestPaymentCancel);
    void shippingPayment(ResponsePaymentShipping responsePaymentShipping);
}

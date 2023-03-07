package com.example.Starbucks.payment.service;

import com.example.Starbucks.payment.vo.*;

import java.time.LocalDateTime;
import java.util.List;

public interface IPaymentService {
    ResponsePayment addPayment(RequestPayment requestPayment);
    //ResponseUser getUser(Long id);
    void cancelPayment(RequestPaymentCancel requestPaymentCancel);
    void shippingPayment(ResponsePaymentShipping responsePaymentShipping);
    ResponseShipping getShippingStatus(Long userId);
    //List<ResponsePayment> getPayment(LocalDateTime date1 , LocalDateTime date2, Long userId, Integer type);
}

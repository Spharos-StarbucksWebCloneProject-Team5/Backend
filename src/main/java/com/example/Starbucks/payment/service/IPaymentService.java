package com.example.Starbucks.payment.service;

import com.example.Starbucks.payment.dto.PaymentDto;
import com.example.Starbucks.payment.dto.PaymentShippingDto;
import com.example.Starbucks.payment.dto.ResponseBest;
import com.example.Starbucks.payment.dto.UserShippingDto;
import com.example.Starbucks.payment.model.Payment;
import com.example.Starbucks.payment.vo.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

public interface IPaymentService {
    void addPayment(HttpServletRequest httpServletRequest, RequestPayment requestPayment);
    //ResponseUser getUser(Long id);
    void cancelPayment(RequestPaymentCancel requestPaymentCancel);
    void shippingPayment(PaymentShippingDto paymentShippingDto);
    UserShippingDto getShippingStatus(Long userId);
    List<PaymentDto> getPayment(Long userId, RequestPaymentList requestPaymentList);
    List<ResponseBest> getBest(Integer main);

    void addCartPayment(RequestCartPayment requestCartPayments);
}

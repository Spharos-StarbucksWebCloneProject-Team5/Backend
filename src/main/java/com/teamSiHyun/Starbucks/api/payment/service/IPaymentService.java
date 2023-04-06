package com.teamSiHyun.Starbucks.api.payment.service;

import com.teamSiHyun.Starbucks.api.payment.dto.PaymentDto;
import com.teamSiHyun.Starbucks.api.payment.dto.PaymentShippingDto;
import com.teamSiHyun.Starbucks.api.payment.dto.ResponseBest;
import com.teamSiHyun.Starbucks.api.payment.dto.UserShippingDto;
import com.teamSiHyun.Starbucks.api.payment.vo.RequestCartPayment;
import com.teamSiHyun.Starbucks.api.payment.vo.RequestPayment;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface IPaymentService {
    ResponseEntity<?> addPayment(Authentication authentication, RequestPayment requestPayment);
    //ResponseUser getUser(Long id);
    void cancelPayment(Long id);
    void shippingPayment(PaymentShippingDto paymentShippingDto);
    UserShippingDto getShippingStatus(Authentication authentication);
    List<PaymentDto> getPayment(Authentication authentication, String startDate, String endDate);
    List<ResponseBest> getBest(Integer main);

    void addCartPayment(RequestCartPayment requestCartPayments);
}

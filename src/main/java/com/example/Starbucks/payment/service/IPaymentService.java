package com.example.Starbucks.payment.service;

import com.example.Starbucks.payment.dto.PaymentDto;
import com.example.Starbucks.payment.dto.PaymentShippingDto;
import com.example.Starbucks.payment.dto.ResponseBest;
import com.example.Starbucks.payment.dto.UserShippingDto;
import com.example.Starbucks.payment.model.Payment;
import com.example.Starbucks.payment.vo.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
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

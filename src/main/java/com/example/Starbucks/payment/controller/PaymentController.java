package com.example.Starbucks.payment.controller;

import com.example.Starbucks.payment.service.IPaymentService;
import com.example.Starbucks.payment.vo.RequestPayment;
import com.example.Starbucks.payment.vo.ResponsePayment;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/payment")
@RequiredArgsConstructor

public class PaymentController {
    private final IPaymentService iPaymentService;

    @PostMapping("/add")
    public ResponsePayment addPayment( @RequestBody RequestPayment requestPayment) {
        return iPaymentService.addPayment(requestPayment);
    }
}

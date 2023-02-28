package com.example.Starbucks.payment.controller;

import com.example.Starbucks.payment.service.IPaymentService;
import com.example.Starbucks.payment.vo.RequestPayment;
import com.example.Starbucks.payment.vo.RequestPaymentCancel;
import com.example.Starbucks.payment.vo.ResponsePayment;
import com.example.Starbucks.payment.vo.ResponsePaymentShipping;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/payment")
@RequiredArgsConstructor

public class PaymentController {
    private final IPaymentService iPaymentService;

    @PostMapping("/add")
    public ResponsePayment addPayment( @RequestBody RequestPayment requestPayment) {
        return iPaymentService.addPayment(requestPayment);
    }

    @PutMapping("/cancel")
    public void cancelPayment(@RequestBody RequestPaymentCancel requestPaymentCancel){
        iPaymentService.cancelPayment(requestPaymentCancel);
    }
    @PutMapping("/shipping")
    public void shippingPayment(@RequestBody ResponsePaymentShipping responsePaymentShipping){
        iPaymentService.shippingPayment(responsePaymentShipping);
    }
}

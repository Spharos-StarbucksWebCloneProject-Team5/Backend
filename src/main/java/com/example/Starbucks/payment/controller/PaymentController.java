package com.example.Starbucks.payment.controller;

import com.example.Starbucks.payment.dto.PaymentDto;
import com.example.Starbucks.payment.dto.PaymentShippingDto;
import com.example.Starbucks.payment.dto.UserShippingDto;
import com.example.Starbucks.payment.model.Payment;
import com.example.Starbucks.payment.service.IPaymentService;
import com.example.Starbucks.payment.vo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/v1/api/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final IPaymentService iPaymentService;

    @PostMapping("")
    public ResponseEntity<Void> addPayment(@RequestBody @Valid RequestPayment requestPayment) {
        iPaymentService.addPayment(requestPayment);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/cancel")
    public ResponseEntity<Void> cancelPayment(@RequestBody @Valid RequestPaymentCancel requestPaymentCancel){
        iPaymentService.cancelPayment(requestPaymentCancel);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/shipping")
    public ResponseEntity<Void> shippingPayment(@RequestBody @Valid PaymentShippingDto paymentShippingDto){
        iPaymentService.shippingPayment(paymentShippingDto);
        return ResponseEntity.ok().build();
    }
    @ResponseBody
    @GetMapping("shipping/{userId}") //배송상태
    public ResponseEntity<UserShippingDto> getShippingStatus(@PathVariable Long userId){
        return ResponseEntity.ok(iPaymentService.getShippingStatus(userId));
    }
    @ResponseBody
    @GetMapping("/get/{userId}") //주문내역
    public ResponseEntity<List<PaymentDto>> getPayment(@PathVariable Long userId, @RequestBody @Valid RequestPaymentList requestPaymentList){
        return ResponseEntity.ok(iPaymentService.getPayment(userId, requestPaymentList));
    }
}

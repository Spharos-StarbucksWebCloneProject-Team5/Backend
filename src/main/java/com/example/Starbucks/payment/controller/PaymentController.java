package com.example.Starbucks.payment.controller;

import com.example.Starbucks.payment.service.IPaymentService;
import com.example.Starbucks.payment.vo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/v1/api/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final IPaymentService iPaymentService;

    @PostMapping("")
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
    @ResponseBody
    @GetMapping("shipping/{userId}") //배송상태
    public ResponseShipping getShippingStatus(@PathVariable Long userId){
        return iPaymentService.getShippingStatus(userId);
    }
    /*@ResponseBody
    @GetMapping("{date1}&{date2}&{userId}&{type}") //주문내역
    public List<ResponsePayment> getPayment(@PathVariable LocalDateTime date1, @PathVariable LocalDateTime date2
            , @PathVariable Long userId, @PathVariable Integer type){
        return iPaymentService.getPayment(date1,date2,userId,type);
    }*/
}

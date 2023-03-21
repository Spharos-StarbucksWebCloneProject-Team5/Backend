package com.example.Starbucks.payment.controller;

import com.example.Starbucks.payment.dto.PaymentDto;
import com.example.Starbucks.payment.dto.PaymentShippingDto;
import com.example.Starbucks.payment.dto.UserShippingDto;
import com.example.Starbucks.payment.model.Payment;
import com.example.Starbucks.payment.service.IPaymentService;
import com.example.Starbucks.payment.vo.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Tag(name = "주문")
@RestController
@RequestMapping("/v1/api/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final IPaymentService iPaymentService;

    @Operation(summary = "주문 요청", description = "주문을 등록합니다.", tags = { "payment Controller" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
    })
    @PostMapping("")
    public ResponseEntity<Void> addPayment(@RequestBody @Valid RequestPayment requestPayment) {
        iPaymentService.addPayment(requestPayment);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "주문 취소 요청", description = "주문을 취소합니다.", tags = { "payment Controller" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
    })
    @PutMapping("/cancel")
    public ResponseEntity<Void> cancelPayment(@RequestBody @Valid RequestPaymentCancel requestPaymentCancel){
        iPaymentService.cancelPayment(requestPaymentCancel);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "해당 주문 배송 상태 변경 요청", description = "해당 주문의 배송 상태를 변경합니다.", tags = { "payment Controller" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
    })
    @PutMapping("/shipping")
    public ResponseEntity<Void> shippingPayment(@RequestBody @Valid PaymentShippingDto paymentShippingDto){
        iPaymentService.shippingPayment(paymentShippingDto);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "최근 3개월 배송 상태 요청", description = "최근 3개월 배송 상태를 가져옵니다.", tags = { "payment Controller" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
    })
    @ResponseBody
    @GetMapping("shipping/{userId}") //배송상태
    public ResponseEntity<UserShippingDto> getShippingStatus(@PathVariable Long userId){
        return ResponseEntity.ok(iPaymentService.getShippingStatus(userId));
    }

    @Operation(summary = "주문내역 요청", description = "주문내역을 가져옵니다.", tags = { "payment Controller" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
    })
    @ResponseBody
    @GetMapping("/get/{userId}") //주문내역
    public ResponseEntity<List<PaymentDto>> getPayment(@PathVariable Long userId, @RequestBody @Valid RequestPaymentList requestPaymentList){
        return ResponseEntity.ok(iPaymentService.getPayment(userId, requestPaymentList));
    }
}

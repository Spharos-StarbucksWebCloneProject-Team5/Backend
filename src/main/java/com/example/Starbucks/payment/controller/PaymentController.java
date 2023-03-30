package com.example.Starbucks.payment.controller;

import com.example.Starbucks.payment.dto.PaymentDto;
import com.example.Starbucks.payment.dto.PaymentShippingDto;
import com.example.Starbucks.payment.dto.ResponseBest;
import com.example.Starbucks.payment.dto.UserShippingDto;
import com.example.Starbucks.payment.model.Payment;
import com.example.Starbucks.payment.service.IPaymentService;
import com.example.Starbucks.payment.vo.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("/v1/api/payments")
@RequiredArgsConstructor
@Slf4j
public class PaymentController {
    private final IPaymentService iPaymentService;

    @Operation(summary = "베스트 상품", description = "결제 수에 따른 베스트 상품을 반환합니다.", tags = { "상품" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
    })
    @GetMapping("/best")
    public ResponseEntity<List<ResponseBest>> getBest(@Param("main") Integer main) {
        return ResponseEntity.ok(iPaymentService.getBest(main));
    }

    @Operation(summary = "주문 요청", description = "주문을 등록합니다.", tags = { "주문" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
    })
    @PostMapping("")
    public ResponseEntity<Void> addPayment(HttpServletRequest httpServletRequest, @RequestBody @Valid RequestPayment requestPayment) {
        iPaymentService.addPayment(httpServletRequest,requestPayment);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "주문 취소 요청", description = "주문을 취소합니다.", tags = { "주문" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
    })
    @PutMapping("/cancel")
    public ResponseEntity<Void> cancelPayment(@RequestBody @Valid RequestPaymentCancel requestPaymentCancel){
        iPaymentService.cancelPayment(requestPaymentCancel);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "해당 주문 배송 상태 변경 요청", description = "해당 주문의 배송 상태를 변경합니다.", tags = { "주문" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
    })
    @PutMapping("/shipping")
    public ResponseEntity<Void> shippingPayment(@RequestBody @Valid PaymentShippingDto paymentShippingDto){
        iPaymentService.shippingPayment(paymentShippingDto);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "최근 3개월 배송 상태 요청", description = "최근 3개월 배송 상태를 가져옵니다.", tags = { "주문" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
    })
    @ResponseBody
    @GetMapping("shipping/{userId}") //배송상태
    public ResponseEntity<UserShippingDto> getShippingStatus(@PathVariable Long userId){
        return ResponseEntity.ok(iPaymentService.getShippingStatus(userId));
    }

    @Operation(summary = "주문내역 요청", description = "주문내역을 가져옵니다.", tags = { "주문" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
    })
    @ResponseBody
    @GetMapping("/get/{userId}") //주문내역
    public ResponseEntity<List<PaymentDto>> getPayment(@PathVariable Long userId, @RequestBody RequestPaymentList requestPaymentList){
        return ResponseEntity.ok(iPaymentService.getPayment(userId, requestPaymentList));
    }

    @Operation(summary = "카트 상품 결제", description = "고객 카트 상품 한번에 결제하기.", tags = { "주문" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
    })
    @ResponseBody
    @PostMapping("/carts")
    public ResponseEntity<Void> addCartPayment(@RequestBody RequestCartPayment requestCartPayments){
        log.info(requestCartPayments.toString());
        iPaymentService.addCartPayment(requestCartPayments);
        return ResponseEntity.ok().build();
    }
}

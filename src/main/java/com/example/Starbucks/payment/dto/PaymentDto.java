package com.example.Starbucks.payment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDto {
    LocalDateTime date;
    Integer payStatus;
    Long productId;
    String productName;
    Integer productPrice;
    String productThumbnail;
    Integer count;
}

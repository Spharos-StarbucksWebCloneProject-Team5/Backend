package com.example.Starbucks.payment.vo;

import lombok.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponsePayment {
    //보내줄 것들
    private Long userId;
    private Long productId;
    private Integer productPrice;
    private Integer product_count;
    private Integer shipping_status;
    private String shipping_address;
    private String shipping_phone;
    private Integer pay_type;
    private Integer pay_status;
    private Integer amount;

}

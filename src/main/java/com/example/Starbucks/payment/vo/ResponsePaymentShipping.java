package com.example.Starbucks.payment.vo;

import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.criteria.CriteriaBuilder;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponsePaymentShipping {
    private Long id;
    private Integer shipping_status;

}

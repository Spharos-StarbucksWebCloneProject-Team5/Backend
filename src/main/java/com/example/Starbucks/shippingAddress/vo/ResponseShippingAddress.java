package com.example.Starbucks.shippingAddress.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseShippingAddress {
    private Long id;

    private String nickname;

    private String  receiver;

    private Integer zipCode;

    private String address;

    private String detailAddress;

    private String shippingPhone1;

    private String shippingPhone2;

    private String shippingMemo;

    private Boolean choiceMain;
}

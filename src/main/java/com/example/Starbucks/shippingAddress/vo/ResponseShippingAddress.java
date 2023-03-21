package com.example.Starbucks.shippingAddress.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseShippingAddress {
    @Schema(description = "배송지 고유번호")
    private Long id;
    @Schema(description = "배송지 주소")
    private String address;
    @Schema(description = "기본 배송지 여부")
    private Boolean choiceMain;
}

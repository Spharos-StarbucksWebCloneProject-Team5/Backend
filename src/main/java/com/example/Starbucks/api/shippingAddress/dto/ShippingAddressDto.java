package com.example.Starbucks.api.shippingAddress.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class ShippingAddressDto {

    @Schema(description = "배송지 고유 ID")
    private Long id;
    @Schema(description = "배송지 주소")
    private String Address;
    @Schema(description = "기본배송지 여부")
    private Boolean choiceMain;
}

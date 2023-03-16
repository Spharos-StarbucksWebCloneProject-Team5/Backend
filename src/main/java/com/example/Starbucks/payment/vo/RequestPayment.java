package com.example.Starbucks.payment.vo;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.*;

@Getter
@Setter
@ToString
public class RequestPayment {
    //받아올 것들
    @NotNull @Min(0)
    private Long userId;
    @NotNull @Min(0)
    private Long productId;
    @NotNull @Min(0)
    private Integer productCount;
    @NotBlank
    private String  receiver;
    @NotBlank
    private String shippingAddress;
    @NotBlank @Size(min=12,max=13)
    private String shippingPhone;
    @NotNull @Max(1)
    private Integer payType;
}

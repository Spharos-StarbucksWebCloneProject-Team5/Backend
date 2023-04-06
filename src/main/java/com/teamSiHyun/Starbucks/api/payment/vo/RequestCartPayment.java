package com.teamSiHyun.Starbucks.api.payment.vo;

import lombok.*;

import javax.validation.constraints.*;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RequestCartPayment {
    private List<Carts> carts;

    private Long shippingAddressId;

    private Integer payType;

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Carts{
        @Min(0)
        private Long cartId;
    }

    public static List<Carts> ofCarts(List<ICarts> iCarts){
        return iCarts.stream()
                .map(cart -> RequestCartPayment.Carts.builder()
                        .cartId(cart.getCartId())
                        .build())
                .collect(Collectors.toList());
    }
}

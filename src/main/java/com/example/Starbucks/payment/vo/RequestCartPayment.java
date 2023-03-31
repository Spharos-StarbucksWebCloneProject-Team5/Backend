package com.example.Starbucks.payment.vo;

import com.example.Starbucks.cart.model.Cart;
import com.example.Starbucks.category.dto.ResponsePage;
import com.example.Starbucks.category.projection.IProduct;
import io.swagger.v3.oas.annotations.media.Schema;
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

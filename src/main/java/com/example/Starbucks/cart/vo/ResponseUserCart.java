package com.example.Starbucks.cart.vo;

import com.example.Starbucks.cart.model.Cart;
import lombok.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseUserCart {
    Long productId;
    String productName;
    Integer productPrice;
    String productThumbnail;
    Integer count;
}

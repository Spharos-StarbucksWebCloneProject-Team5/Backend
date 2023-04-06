package com.teamSiHyun.Starbucks.api.cart.model;

import com.teamSiHyun.Starbucks.api.product.model.Product;
import com.teamSiHyun.Starbucks.api.users.model.utility.BaseTimeEntity;
import com.teamSiHyun.Starbucks.api.users.model.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;


import javax.persistence.*;
import javax.validation.Valid;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Valid
public class Cart extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "카트아이디")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @Schema(description = "유저정보")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @Schema(description = "상품정보")
    private Product product;

    @Schema(description = "카트에 담은 수량")
    private Integer count;

    @Schema(description = "현재 카트 존재여부")
    private Boolean now;
    //삭제시 false 반환

    public void setUpdateCount(Integer count) {
        this.count = count;
    }

    public void setUpdateNow(Boolean now) {
        this.now = now;
    }
}

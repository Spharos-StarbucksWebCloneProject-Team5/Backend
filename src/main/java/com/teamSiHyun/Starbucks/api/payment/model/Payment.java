package com.teamSiHyun.Starbucks.api.payment.model;

import com.teamSiHyun.Starbucks.api.product.model.Product;
import com.teamSiHyun.Starbucks.api.users.model.User;
import com.teamSiHyun.Starbucks.api.users.model.utility.BaseTimeEntity;
import com.teamSiHyun.Starbucks.api.shippingAddress.model.ShippingAddress;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Payment extends BaseTimeEntity {
    @Schema(description = "주문 고유 ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "유저")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Schema(description = "상품")
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    @Schema(description = "상품 개수")
    private Integer productCount;

    @OneToOne
    @Schema(description = "배송 정보")
    private ShippingAddress shippingAddress;

    private Integer shippingStatus;

    @Schema(description = "결제 방법")
    private Integer payType;

    @Schema(description = "주문 금액")
    private Integer amount;

    @Schema(description = "결제 상태")
    private Integer payStatus;
    //결제완료, 결제 취소
}

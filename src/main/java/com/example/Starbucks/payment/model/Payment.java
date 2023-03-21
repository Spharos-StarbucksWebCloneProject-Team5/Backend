package com.example.Starbucks.payment.model;

import com.example.Starbucks.users.model.utility.BaseTimeEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import com.example.Starbucks.users.model.User;
import com.example.Starbucks.product.model.Product;

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

    @Schema(description = "받는사람")
    private String  receiver;

    @Schema(description = "배송지 주소")
    private String shippingAddress;

    @Schema(description = "연락처")
    private String shippingPhone;

    @Schema(description = "배송상태")
    private Integer shippingStatus;

    @Schema(description = "결제 방법")
    private Integer payType;

    @Schema(description = "주문 금액")
    private Integer amount;

    @Schema(description = "결제 상태")
    private Integer payStatus;
    //결제완료, 결제 취소
}

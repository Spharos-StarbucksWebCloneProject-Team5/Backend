package com.example.Starbucks.shippingAddress.model;

import com.example.Starbucks.users.model.User;
import com.example.Starbucks.users.model.utility.BaseTimeEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ShippingAddress extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "배송지 고유 ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    private String nickname;

    private String  receiver;

    private Integer zipCode;

    @Schema(description = "배송지 주소")
    private String address;

    private String detailAddress;

    private String shippingPhone1;

    private String shippingPhone2;

    private String shippingMemo;

    @Schema(description = "기본배송지 여부")
    private Boolean choiceMain;
}

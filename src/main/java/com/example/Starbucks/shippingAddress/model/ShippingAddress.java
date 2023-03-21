package com.example.Starbucks.shippingAddress.model;

import com.example.Starbucks.users.model.utility.BaseTimeEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
    @Schema(description = "배송지 주소")
    private String address;
    @Schema(description = "기본배송지 여부")
    private Boolean choiceMain;
}

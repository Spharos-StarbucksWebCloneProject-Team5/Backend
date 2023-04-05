package com.example.Starbucks.api.coupon.model;

import com.example.Starbucks.api.users.model.utility.BaseTimeEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Coupon extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "쿠폰 아이디")
    private Long id;

    @Schema(description = "쿠폰명")
    private String name;

    @Schema(description = "쿠폰 할인율")
    private Integer discount;

    @Schema(description = "쿠폰 만료일")
    private Date end_date;

    @Schema(description = "쿠폰 사용 상태")
    private Integer status;

    @Schema(description = "쿠폰 종류")
    private Integer type;

}

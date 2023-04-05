package com.teamSiHyun.Starbucks.api.event.model;

import com.teamSiHyun.Starbucks.api.product.model.Product;
import com.teamSiHyun.Starbucks.api.users.model.utility.BaseTimeEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventProduct extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "이벤트상품 인덱스")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @Schema(description = "이벤트상품 정보")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @Schema(description = "이벤트정보")
    private Event event;
}

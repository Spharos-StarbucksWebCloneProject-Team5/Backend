package com.example.Starbucks.api.event.model;

import com.example.Starbucks.api.users.model.utility.BaseTimeEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventImageList extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "이벤트이미지 아이디")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @Schema(description = "이벤트 정보")
    private Event event;

    @Schema(description = "이벤트 이미지")
    private String image;
}

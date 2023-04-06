package com.teamSiHyun.Starbucks.api.event.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventDto {
    @Schema(description = "진행이벤트순서")
    private Long id;

    @Schema(description = "이벤트 아이디")
    private Long eventId;

    @Schema(description = "이벤트명")
    private String name;

    @Schema(description = "이벤트 설명")
    private String description;

    @Schema(description = "이벤트 대표이미지")
    private String titleImage;
}

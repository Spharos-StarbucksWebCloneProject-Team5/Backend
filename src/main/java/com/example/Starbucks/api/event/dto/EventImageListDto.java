package com.example.Starbucks.api.event.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventImageListDto {

    @Schema(description = "이벤트이미지 아이디")
    private Long id;

    @Schema(description = "이벤트 아이디")
    private Long eventId;

    @Schema(description = "이벤트 아이디")
    private String description;

    @Schema(description = "이미지 정보")
    private String image;
}

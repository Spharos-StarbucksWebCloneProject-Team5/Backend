package com.example.Starbucks.event.dto;

import com.example.Starbucks.event.model.Event;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

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

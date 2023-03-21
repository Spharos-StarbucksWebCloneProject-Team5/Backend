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
    @Schema(description = "이벤트아이디")
    private Long id;

    @Schema(description = "이벤트명")
    private String name;

    @Schema(description = "이벤트 설명")
    private String description;

    @Schema(description = "이벤트 대표이미지")
    private String titleImage;

    @Schema(description = "이벤트 이미지정보")
    private String infoImage;

    @Schema(description = "이벤트 시작일")
    private Date startDate;

    @Schema(description = "이벤트종료일")
    private Date endDate;
}

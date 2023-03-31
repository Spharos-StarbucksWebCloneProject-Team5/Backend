package com.example.Starbucks.event.model;

import com.example.Starbucks.users.model.utility.BaseTimeEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Event extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "이벤트아이디")
    private Long id;

    @Schema(description = "이벤트명")
    private String name;

    @Schema(description = "이벤트상세설명")
    private String description;

    @Schema(description = "이벤트대표이미지")
    private String titleImage;

    @Schema(description = "이벤트이미지 정보")
    private String infoImage;

    @Schema(description = "이벤트 시작일")
    private Date startDate;

    @Schema(description = "이벤트 종료일")
    private Date endDate;

    @Schema(description = "이벤트 노출여부")
    private Boolean now;

}

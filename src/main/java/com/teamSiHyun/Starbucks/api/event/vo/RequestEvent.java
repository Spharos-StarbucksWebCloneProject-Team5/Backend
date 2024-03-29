package com.teamSiHyun.Starbucks.api.event.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestEvent {

    @NotBlank
    private String name;
    @NotEmpty
    private String description;
    @NotBlank
    private String titleImage;

    private String infoImage;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private Date startDate;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private Date endDate;
    //@NotNull
    private Boolean now;
}

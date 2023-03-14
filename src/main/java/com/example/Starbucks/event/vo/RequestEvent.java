package com.example.Starbucks.event.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RequestEvent {

    @NotBlank
    private String name;
    @NotEmpty
    private String description;
    @NotBlank
    private String title_image;
    @NotBlank
    private String info_image;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private Date start_date;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private Date end_date;
    @NotNull
    private boolean isNow;
}

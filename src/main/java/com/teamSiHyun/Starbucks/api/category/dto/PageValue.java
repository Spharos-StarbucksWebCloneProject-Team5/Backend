package com.teamSiHyun.Starbucks.api.category.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageValue {
    private Integer pageNum;
    @Schema(description = "페이지당 element 크기")
    private Integer pageSize;
    @Schema(description = "총 페이지 수")
    private Integer totalPage;
    @Schema(description = "총 element 수")
    private Long totalElements;
}

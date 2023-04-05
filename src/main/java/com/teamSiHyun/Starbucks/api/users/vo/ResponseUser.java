package com.teamSiHyun.Starbucks.api.users.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseUser {
    @Schema(description = "유저 고유번호")
    private Long id;
    @Schema(description = "유저 이름")
    private String name;
    @Schema(description = "유저 이메일")
    private String email;
    @Schema(description = "유저 주소")
    private String address;
}

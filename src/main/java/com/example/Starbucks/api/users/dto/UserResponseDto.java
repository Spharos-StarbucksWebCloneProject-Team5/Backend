package com.example.Starbucks.api.users.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class UserResponseDto {
    @Builder
    @Getter
    @AllArgsConstructor
    public static class TokenInfo {
        @Schema(description = "Authorization 타입")
        private String grantType;
        @Schema(description = "JWT AccessToken")
        private String accessToken;
        @Schema(description = "JWT RefreshToken")
        private String refreshToken;
        @Schema(description = "RefreshToken 만료시간")
        private Long refreshTokenExpirationTime;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class userName {
        private String name;
    }
}

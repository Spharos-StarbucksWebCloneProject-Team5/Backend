package com.example.Starbucks.users.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class UserResponseDto {
    @Builder
    @Getter
    @AllArgsConstructor
    public static class TokenInfo {
        private String grantType;
        private String accessToken;
        private String refreshToken;
        private Long refreshTokenExpirationTime;
    }
}

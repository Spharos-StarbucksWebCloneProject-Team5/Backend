package com.example.Starbucks.users.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseUser {
    private Long id;
    private String name;
    private String email;
    private String address;
}

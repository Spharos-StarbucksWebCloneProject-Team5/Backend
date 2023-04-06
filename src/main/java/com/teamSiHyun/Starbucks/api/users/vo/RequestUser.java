package com.teamSiHyun.Starbucks.api.users.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestUser {
    private String name;
    private String email;
    private String password;
}

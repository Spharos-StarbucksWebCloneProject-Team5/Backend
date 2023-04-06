package com.teamSiHyun.Starbucks.api.users.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@Slf4j
public class TestController {
    @PostMapping("/test")
    public String test() {
            return "/test 통과";
    }

    @PostMapping("/admin/")
    public String test1(){
        return "admin 통과";
    }
}

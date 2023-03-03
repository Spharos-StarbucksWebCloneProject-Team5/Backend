package com.example.Starbucks.users.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @PostMapping("/test")
    public String test(){
        return "/test 통과";
    }

    @PostMapping("/admin/test")
    public String test1(){
        return "admin 통과";
    }
}

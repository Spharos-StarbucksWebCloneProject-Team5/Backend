package com.example.Starbucks.OAuth;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController{

    @RequestMapping("/main")
    public String main(){
        return "redirect:http://3.36.128.190:6600";
    }
}
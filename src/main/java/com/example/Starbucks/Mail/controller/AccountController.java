package com.example.Starbucks.Mail.controller;


import com.example.Starbucks.Mail.service.MailServiceImpl;
import com.fasterxml.jackson.databind.util.JSONPObject;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/api")
@Slf4j
@RequiredArgsConstructor
public class AccountController {
    private final MailServiceImpl registerMail;

    @PostMapping("/email")
    public String emailConfirm(@RequestParam String email) throws Exception{
        String code = registerMail.sendSimpleMessage(email);

        return code;
    }

}

package com.example.Starbucks.Mail.controller;


import com.example.Starbucks.Mail.dto.EmailCodeDto;
import com.example.Starbucks.Mail.dto.EmailDto;
import com.example.Starbucks.Mail.service.MailServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
@Slf4j
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class AccountController {
    private final MailServiceImpl registerMail;

    @PostMapping("/email")
    public String sendEmail(@RequestBody EmailDto to) throws Exception{
        String code = registerMail.sendSimpleMessage(to);

        return code;
    }

    @PostMapping("/email-confirm")
    public String emailConfirm(@RequestBody EmailCodeDto emailCodeDto) throws Exception{
        String code = registerMail.verifyEmail(emailCodeDto.getCode());

        return code;
    }


}

package com.example.Starbucks.Mail.controller;


import com.example.Starbucks.Mail.dto.EmailCodeDto;
import com.example.Starbucks.Mail.dto.EmailDto;
import com.example.Starbucks.Mail.service.MailServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
@Slf4j
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class AccountController {
    private final MailServiceImpl registerMail;

    @Operation(summary = "메일 인증 요청", description = "입력한 메일로 인증메일 발송합니다.", tags = { "이메일" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
    })
    @PostMapping("/email")
    public ResponseEntity<?> sendEmail(@RequestBody EmailDto to) throws Exception{
        registerMail.sendSimpleMessage(to);
        return ResponseEntity.ok().body("메일 인증 요청");
    }

    @Operation(summary = "인증코드 확인 요청", description = "메일로 발송된 인증코드와 입력된 코드를 확인합니다.", tags = { "이메일" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
    })
    @PostMapping("/email-confirm")
    public ResponseEntity<?> emailConfirm(@RequestBody EmailCodeDto emailCodeDto) throws Exception{
        return registerMail.verifyEmail(emailCodeDto.getCode());
    }


}

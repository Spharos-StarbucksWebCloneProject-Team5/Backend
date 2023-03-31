package com.example.Starbucks.users.controller;

import com.example.Starbucks.Mail.service.MailService;
import com.example.Starbucks.jwt.JwtTokenProvider;
import com.example.Starbucks.lib.Helper;
import com.example.Starbucks.users.Response;
import com.example.Starbucks.users.dto.UserRequestDto;
import com.example.Starbucks.users.service.UserService;
import com.example.Starbucks.users.vo.RequestEmailCheck;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {


    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;
    private final Response response;
    private final MailService mailService;

    @Operation(summary = "이메일 중복 체크", description = "이메일 중복 체크", tags = { "유저" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST")
    })
    @PostMapping("/email")
    public ResponseEntity<?> emailCheck(@RequestBody RequestEmailCheck requestEmailCheck) {
        return userService.emailCheck(requestEmailCheck.getEmail());
    }

    @Operation(summary = "회원 가입 요청", description = "회원 가입 요청.", tags = { "유저" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST")
    })
    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@Validated @RequestBody UserRequestDto.SignUp signUp, Errors errors) {
        // validation check
        if (errors.hasErrors()) {
            return response.invalidFields(Helper.refineErrors(errors));
        }
        return userService.signUp(signUp);
    }

    @Operation(summary = "패스워드 수정 요청", description = "패스워드 수정 요청", tags = { "유저" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST")
    })
    @PutMapping("/modify")
    public ResponseEntity<?> modify(@Validated @RequestBody UserRequestDto.PasswordModify passwordModify, Errors errors) {
        // validation check
        if (errors.hasErrors()) {
            return response.invalidFields(Helper.refineErrors(errors));
        }
        return userService.modifyPassword(passwordModify);
    }

    @Operation(summary = "로그인 요청", description = "로그인이 성공하면 JWT(AT,RT 발급)", tags = { "유저" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST")
    })
    @PostMapping("/login")
    public ResponseEntity<?> login(@Validated @RequestBody UserRequestDto.Login login, Errors errors, HttpServletResponse httpServletResponse) {
        // validation check
        if (errors.hasErrors()) {
            return response.invalidFields(Helper.refineErrors(errors));
        }
        return userService.login(login, httpServletResponse);
    }

    @Operation(summary = "Access 토큰 재발급", description = "Access토큰과 Refresh토큰을 통해 Access토큰을 재발급 합니다.", tags = { "유저" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST")
    })
    @PostMapping("/reissue")
    public ResponseEntity<?> reissue(@RequestHeader("accessToken") String accessToken, @RequestHeader("refreshToken") String refreshToken, HttpServletResponse httpServletResponse) {
        // validation check
//        if (errors.hasErrors()) {
//            return response.invalidFields(Helper.refineErrors(errors));
//        }
        return userService.reissue(accessToken, refreshToken, httpServletResponse);
    }

    @Operation(summary = "로그아웃 요청", description = "로그아웃 요청이 오면 토큰이 유효한지 검증하고, 로그아웃 처리", tags = { "유저" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST")
    })
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest httpServletRequest) {
        // validation check
//        if (errors.hasErrors()) {
//            return response.invalidFields(Helper.refineErrors(errors));
//        }
        return userService.logout(httpServletRequest);
    }

    @Operation(summary = "Admin 권한 부여", description = "Admin 권한 부여", tags = { "유저" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST")
    })
    @GetMapping("/authority")
    public ResponseEntity<?> authority() {
        log.info("ADD ROLE_ADMIN");
        return userService.authority();
    }


    @GetMapping("/userTest")
    public ResponseEntity<?> userTest() {
        log.info("ROLE_USER TEST");
        return response.success();
    }

    @GetMapping("/adminTest")
    public ResponseEntity<?> adminTest() {
        log.info("ROLE_ADMIN TEST");
        return response.success();
    }
}


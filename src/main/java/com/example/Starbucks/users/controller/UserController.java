package com.example.Starbucks.users.controller;

import com.example.Starbucks.jwt.JwtTokenProvider;
import com.example.Starbucks.lib.Helper;
import com.example.Starbucks.users.Response;
import com.example.Starbucks.users.dto.UserRequestDto;
import com.example.Starbucks.users.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {


    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;
    private final Response response;

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@Validated UserRequestDto.SignUp signUp, Errors errors) {
        // validation check
        if (errors.hasErrors()) {
            return response.invalidFields(Helper.refineErrors(errors));
        }
        return userService.signUp(signUp);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Validated UserRequestDto.Login login, Errors errors) {
        // validation check
        if (errors.hasErrors()) {
            return response.invalidFields(Helper.refineErrors(errors));
        }
        return userService.login(login);
    }

    @PostMapping("/reissue")
    public ResponseEntity<?> reissue(@Validated UserRequestDto.Reissue reissue, Errors errors) {
        // validation check
        if (errors.hasErrors()) {
            return response.invalidFields(Helper.refineErrors(errors));
        }
        return userService.reissue(reissue);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@Validated UserRequestDto.Logout logout, Errors errors) {
        // validation check
        if (errors.hasErrors()) {
            return response.invalidFields(Helper.refineErrors(errors));
        }
        return userService.logout(logout);
    }

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


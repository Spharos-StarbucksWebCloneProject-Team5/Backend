package com.teamSiHyun.Starbucks.api.users.service;

//import com.example.Starbucks.users.dto.UserInfoDto;

import com.teamSiHyun.Starbucks.api.users.dto.UserRequestDto;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface UserService {
//    ResponseUser addUser(RequestUser requestUser);
//    ResponseUser getUser(Long id);

    public ResponseEntity<?> signUp(UserRequestDto.SignUp signUp);

    public ResponseEntity<?> login(UserRequestDto.Login login, HttpServletResponse httpServletResponse);

    public ResponseEntity<?> kakaoLogin(String email, HttpServletResponse httpServletResponse);

    public ResponseEntity<?> reissue(String accessToken, String refreshToken, HttpServletResponse httpServletResponse);

    public ResponseEntity<?> logout(HttpServletRequest httpServletRequest);

    public ResponseEntity<?> authority();

    public ResponseEntity<?> check(String check, String name);

    public ResponseEntity<?> emailCheck(String email);

    public ResponseEntity<?> modifyPassword(UserRequestDto.PasswordModify passwordModify);


}

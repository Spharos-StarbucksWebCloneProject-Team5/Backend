package com.example.Starbucks.users.controller;

//import com.example.Starbucks.jwt.JwtTokenProvider;
//import com.example.Starbucks.users.dto.UserResponseDto;
import com.example.Starbucks.users.model.User;
import com.example.Starbucks.users.repository.UserRepository;
import com.example.Starbucks.users.service.UserService;
import com.example.Starbucks.users.vo.RequestUser;
import com.example.Starbucks.users.vo.ResponseUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.security.SecurityUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Security;
import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    private final UserService userService;
//    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;

    @PostMapping("/add")
    public ResponseUser addUser(@RequestBody RequestUser requestUser){

        return userService.addUser(requestUser);
    }

    @GetMapping("/get/{id}")
    public ResponseUser getUser(@PathVariable Long id){
        log.info("input id => {}",id);
        return userService.getUser(id);

    }


//    final String EMAIL = "aabbcc@gmail.com";
//    final String NAME = "침착맨";
//
//    User user = User.builder()
//            .email(EMAIL)
//            .name(NAME)
//            .roles(Collections.singletonList("ROLE_USER")) // 최초 가입시 USER 로 설정
//            .build();
//
//
//    @PostMapping("/join")
//    public String join(){
//        userRepository.save(user);
//        return user.toString();
//
//    }

    // 로그인
//    @PostMapping("/login")
//    public String login(@RequestBody Map<String, String> user) {
//        log.info("user email = {}", user.get("email"));
//        User member = userRepository.findByEmail(user.get("email"))
//                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 E-MAIL 입니다."));
//
//        return jwtTokenProvider.createToken(member.getUsername(), member.getRoles());
//    }


}

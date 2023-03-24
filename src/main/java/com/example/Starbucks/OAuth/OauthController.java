package com.example.Starbucks.OAuth;

import com.example.Starbucks.users.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

@RestController
@RequiredArgsConstructor
@RequestMapping("/login")
public class OauthController {
    private final KakaoAPI kakaoAPI;
//    private final OAuthService oAuthService;
    private final UserService userService;
    @ResponseBody
    @GetMapping("/kakao-test")
    public ResponseEntity<String> login(@RequestParam("code") String code, HttpSession session){
        System.out.println(code);
        String res = "accesstoken 생성 완료";
        System.out.println("code :" + code);
        String access_Token = kakaoAPI.getAccessToken(code);
        HashMap<String,Object> userInfo = kakaoAPI.getUserInfo(access_Token);

        System.out.println("userInfo = " + userInfo);

        if(userInfo.get("email") != null){
            session.setAttribute("userId",userInfo.get("email"));
            session.setAttribute("AT",access_Token);
        }
//        System.out.println("access_Token = " + access_Token);

        // 회원 인지확인
        userService.check(userInfo.get("email").toString(),userInfo.get("nickname").toString());


        return ResponseEntity.ok(res);
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        kakaoAPI.kakaoLogout((String)session.getAttribute("AT"));
        session.removeAttribute("AT");
        session.removeAttribute("userId");
        return "로그아웃완료";
    }



}

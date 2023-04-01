package com.example.Starbucks.OAuth;

import com.example.Starbucks.users.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;

@Controller
@RequiredArgsConstructor
@RequestMapping("/login")
public class OauthController {
    private final KakaoAPI kakaoAPI;
//    private final OAuthService oAuthService;
    private final UserService userService;

    @Operation(summary = "OAuth Kakao login", description = "OAuth Kakao login 요청을 하면 code 발급후 code로 access토큰 받아와서 해당 유저의 email, nickname을 받아온다. 가져온 정보로 DB에 저장된 정보와 비교해서 체크한다.", tags = { "유저" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST")
    })
    @ResponseBody
    @GetMapping("/kakao")
    public ModelAndView login(@RequestParam("code") String code, HttpServletResponse httpServletResponse) throws URISyntaxException {
        System.out.println(code);
        String res = "accesstoken 생성 완료";
        System.out.println("code :" + code);
        String access_Token = kakaoAPI.getAccessToken(code);
        HashMap<String,Object> userInfo = kakaoAPI.getUserInfo(access_Token);

        System.out.println("userInfo = " + userInfo);

//        if(userInfo.get("email") != null){
//            session.setAttribute("userId",userInfo.get("email"));
//            session.setAttribute("AT",access_Token);
//        }
//        System.out.println("access_Token = " + access_Token);

        //kakao login jwt생성
        userService.check(userInfo.get("email").toString(),userInfo.get("nickname").toString());
        // 회원인지 확인
        userService.kakaoLogin(userInfo.get("email").toString(),httpServletResponse);
        return new ModelAndView("redirect:/main");
    }
    @Operation(summary = "OAuth Kakao logout", description = "OAuth Kakao logout", tags = { "유저" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST")
    })
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        kakaoAPI.kakaoLogout((String)session.getAttribute("AT"));
        session.removeAttribute("AT");
        session.removeAttribute("userId");
        return "로그아웃완료";
    }

}


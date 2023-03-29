package com.example.Starbucks.users.service;

import com.example.Starbucks.Security.SecurityUtil;
import com.example.Starbucks.enums.Authority;
import com.example.Starbucks.jwt.JwtTokenProvider;
import com.example.Starbucks.users.Response;
import com.example.Starbucks.users.dto.UserRequestDto;
import com.example.Starbucks.users.dto.UserResponseDto;
import com.example.Starbucks.users.model.User;
import com.example.Starbucks.users.repository.UserRepository;
import com.example.Starbucks.users.vo.RequestUser;
import com.example.Starbucks.users.vo.ResponseUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final Response response;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;
    private final RedisTemplate redisTemplate;

    public ResponseEntity<?> emailCheck(String email) {
        if (userRepository.existsByEmail(email)) {
            return response.fail("이미 회원가입된 이메일입니다.", HttpStatus.BAD_REQUEST);
        }
        return response.success("가입 가능한 이메일입니다.");
    }

    public ResponseEntity<?> signUp(UserRequestDto.SignUp signUp) {
        if (userRepository.existsByEmail(signUp.getEmail())) {
            return response.fail("이미 회원가입된 이메일입니다.", HttpStatus.BAD_REQUEST);
        }

        User user = User.builder()
                .email(signUp.getEmail())
                .password(passwordEncoder.encode(signUp.getPassword()))
                .roles(Collections.singletonList(Authority.ROLE_USER.name()))
                .build();
        userRepository.save(user);

        return response.success("회원가입에 성공했습니다.");
    }

    @Override
    public ResponseEntity<?> modifyPassword(UserRequestDto.PasswordModify passwordModify) {
        if (userRepository.existsByEmail(passwordModify.getEmail())) {
            User user = userRepository.findByEmail(passwordModify.getEmail()).get();
            userRepository.save(
                    User.builder().id(user.getId())
                            .email(user.getEmail())
                            .password(passwordEncoder.encode(passwordModify.getPassword()))
                            .build());
        }else{
            return response.fail("가입되지 않은 이메일입니다.", HttpStatus.BAD_REQUEST);
        }
        return response.success("비밀번호 수정이 완료되었습니다.");
    }

    public ResponseEntity<?> login(UserRequestDto.Login login, HttpServletResponse httpServletResponse) {

        if (userRepository.findByEmail(login.getEmail()).orElse(null) == null) {
            return response.fail("로그인에 실패하였습니다.", HttpStatus.BAD_REQUEST);
        }

        log.info("log 1");
        // 1. Login ID/PW 를 기반으로 Authentication 객체 생성
        // 이때 authentication 는 인증 여부를 확인하는 authenticated 값이 false
        UsernamePasswordAuthenticationToken authenticationToken = login.toAuthentication();

        try{
            // 2. 실제 검증 (사용자 비밀번호 체크)이 이루어지는 부분
            // authenticate 매서드가 실행될 때 CustomUserDetailsService 에서 만든 loadUserByUsername 메서드가 실행
            Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

            // 3. 인증 정보를 기반으로 JWT 토큰 생성
            UserResponseDto.TokenInfo tokenInfo = jwtTokenProvider.generateToken(authentication);

            // 4. RefreshToken Redis 저장 (expirationTime 설정을 통해 자동 삭제 처리)
            redisTemplate.opsForValue()
                    .set("RT:" + authentication.getName(), tokenInfo.getRefreshToken(), tokenInfo.getRefreshTokenExpirationTime(), TimeUnit.MILLISECONDS);

            httpServletResponse.addHeader("accessToken", tokenInfo.getAccessToken());
            httpServletResponse.addHeader("refreshToken", tokenInfo.getRefreshToken());

            return response.success("로그인에 성공했습니다.");
        } catch(Exception e) {
            return response.fail("로그인에 실패하였습니다.", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> reissue(UserRequestDto.Reissue reissue) {
        // 1. Refresh Token 검증
        if (!jwtTokenProvider.validateToken(reissue.getRefreshToken())) {
            return response.fail("Refresh Token 정보가 유효하지 않습니다.", HttpStatus.BAD_REQUEST);
        }

        // 2. Access Token 에서 User email 을 가져옵니다.
        Authentication authentication = jwtTokenProvider.getAuthentication(reissue.getAccessToken());

        // 3. Redis 에서 User email 을 기반으로 저장된 Refresh Token 값을 가져옵니다.
        String refreshToken = (String)redisTemplate.opsForValue().get("RT:" + authentication.getName());
        // (추가) 로그아웃되어 Redis 에 RefreshToken 이 존재하지 않는 경우 처리
        if(ObjectUtils.isEmpty(refreshToken)) {
            return response.fail("잘못된 요청입니다.", HttpStatus.BAD_REQUEST);
        }
        if(!refreshToken.equals(reissue.getRefreshToken())) {
            return response.fail("Refresh Token 정보가 일치하지 않습니다.", HttpStatus.BAD_REQUEST);
        }

        // 4. 새로운 토큰 생성
        UserResponseDto.TokenInfo tokenInfo = jwtTokenProvider.generateToken(authentication);

        // 5. RefreshToken Redis 업데이트
        redisTemplate.opsForValue()
.set("RT:" + authentication.getName(), tokenInfo.getRefreshToken(), tokenInfo.getRefreshTokenExpirationTime(), TimeUnit.MILLISECONDS);

        return response.success(tokenInfo, "Token 정보가 갱신되었습니다.", HttpStatus.OK);
    }

    public ResponseEntity<?> logout(HttpServletRequest httpServletRequest) {
        String accessToken = httpServletRequest.getHeader("accessToken");
        log.info("accessToken: " + accessToken);
        // 1. Access Token 검증
        if (!jwtTokenProvider.validateToken(accessToken)) {
            return response.fail("잘못된 요청입니다.", HttpStatus.BAD_REQUEST);
        }

        // 2. Access Token 에서 User email 을 가져옵니다.
        Authentication authentication = jwtTokenProvider.getAuthentication(accessToken);

        // 3. Redis 에서 해당 User email 로 저장된 Refresh Token 이 있는지 여부를 확인 후 있을 경우 삭제합니다.
        if (redisTemplate.opsForValue().get("RT:" + authentication.getName()) != null) {
            // Refresh Token 삭제
            redisTemplate.delete("RT:" + authentication.getName());
        }

        // 4. 해당 Access Token 유효시간 가지고 와서 BlackList 로 저장하기
        Long expiration = jwtTokenProvider.getExpiration(accessToken);
        redisTemplate.opsForValue()
                .set(accessToken, "logout", expiration, TimeUnit.MILLISECONDS);

        return response.success("로그아웃 되었습니다.");
    }

    public ResponseEntity<?> authority() {
        // SecurityContext에 담겨 있는 authentication userEamil 정보
        String userEmail = SecurityUtil.getCurrentUserEmail();

        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("No authentication information."));

        // add ROLE_ADMIN
        user.getRoles().add(Authority.ROLE_ADMIN.name());
        userRepository.save(user);

        return response.success();
    }

    @Override
    public ResponseEntity<?> check(String email,String name){
        User user;
        Optional<User> optionalUser = userRepository.findByEmail(email);
//        String accessToken = userRequest.getAccessToken().getTokenValue();

        if (optionalUser.isPresent()) {
            log.info("info log = {} ", "계정이 존재합니다.");
            user = optionalUser.get();
        } else {
            log.info("info log = {} ", "계정이 존재하지 않습니다.");
            user = User.builder()
                    .email(email)
                    .name(name)
                    .roles(Collections.singletonList(Authority.ROLE_USER.name()))
                    .Oauth("kakao")
                    .build();
            userRepository.save(user);
        }
        return response.success();
    }

}

//package com.example.Starbucks.auth;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/api/v1/auth")
//@RequiredArgsConstructor
//public class AuthenticationController {
//
//    private final AuthenticationService authenticationService;
//
//    @PostMapping("/signup")
//    public ResponseEntity<AuthenticationResponse> signup(
//            @RequestBody SignupRequest signupRequest) {
//        return ResponseEntity.ok(authenticationService.signup(signupRequest));
//    }
//
//    @PostMapping("/authenticate")
//    public ResponseEntity<AuthenticationResponse> authenticate(
//            @RequestBody AuthenticationRequest authenticationRequest) {
//        return ResponseEntity.ok(authenticationService.authenticate(authenticationRequest));
//    }
//
//}

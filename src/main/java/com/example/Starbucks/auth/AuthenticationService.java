//package com.example.Starbucks.auth;
//
////import com.example.Starbucks.config.JwtService;
//import com.example.Starbucks.users.model.Role;
//import com.example.Starbucks.users.model.User;
//import com.example.Starbucks.users.repository.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class AuthenticationService {
//
//        private final UserRepository userRepository;
//        private final PasswordEncoder passwordEncoder;
//        private final JwtService jwtService;
//        private final AuthenticationManager authenticationManager;
//        public AuthenticationResponse signup(SignupRequest signupRequest) {
//            var user = User.builder()
//                    .name(signupRequest.getName())
//                    .password(passwordEncoder.encode(signupRequest.getPassword()))
//                    .email(signupRequest.getEmail())
////                    .role(Role.USER)
//                    .build();
//            userRepository.save(user);
//            var jwtToken = jwtService.generateToken(user);
//            return AuthenticationResponse.builder()
//                    .token(jwtToken)
//                    .build();
//        }
//
//        public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
//            authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(
//                            authenticationRequest.getEmail(),
//                            authenticationRequest.getPassword()
//                    )
//            );
//            var user = userRepository.findByEmail(authenticationRequest.getEmail()).orElseThrow();
//            var jwtToken = jwtService.generateToken(user);
//            return AuthenticationResponse.builder()
//                    .token(jwtToken)
//                    .build();
//        }
//}

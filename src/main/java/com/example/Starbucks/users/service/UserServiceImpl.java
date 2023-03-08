package com.example.Starbucks.users.service;

import com.example.Starbucks.users.model.User;
import com.example.Starbucks.users.repository.UserRepository;
import com.example.Starbucks.users.vo.RequestUser;
import com.example.Starbucks.users.vo.ResponseUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    @Override
    public ResponseUser addUser(RequestUser requestUser) {

        UUID uuid = UUID.randomUUID();
        User user = User.builder()
//                .userEid(uuid.toString())
                .name(requestUser.getName())
                .email(requestUser.getEmail())
//                .roles(Collections.singletonList("ROLE_USER")) // 최초 가입시 USER 로 설정
                .password(requestUser.getPassword())
                .build();

        User resUser = userRepository.save(user);

        ResponseUser responseUser = ResponseUser.builder()
                .id(resUser.getId())
                .name(resUser.getName())
                .email(resUser.getEmail())
                .address(resUser.getAddress())
                .build();

        return responseUser;
    }

    @Override
    public ResponseUser getUser(Long id) {
        User user = userRepository.findById(id).get();
        ResponseUser responseUser = ResponseUser.builder()
                .id(user.getId())
                .address(user.getAddress())
                .name(user.getName())
                .email(user.getEmail())
                .build();
        return responseUser;
    }
}

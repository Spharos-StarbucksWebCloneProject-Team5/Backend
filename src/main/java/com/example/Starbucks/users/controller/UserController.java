package com.example.Starbucks.users.controller;

import com.example.Starbucks.users.model.User;
import com.example.Starbucks.users.service.UserService;
import com.example.Starbucks.users.vo.RequestUser;
import com.example.Starbucks.users.vo.ResponseUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    private final UserService userService;

    @PostMapping("/add")
    public ResponseUser addUser(@RequestBody RequestUser requestUser){

        return userService.addUser(requestUser);
    }

    @GetMapping("/get/{id}")
    public ResponseUser getUser(@PathVariable Long id){
        log.info("input id => {}",id);
        return userService.getUser(id);

    }


}

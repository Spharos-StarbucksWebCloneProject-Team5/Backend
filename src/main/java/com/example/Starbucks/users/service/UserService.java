package com.example.Starbucks.users.service;

//import com.example.Starbucks.users.dto.UserInfoDto;
import com.example.Starbucks.users.model.User;
import com.example.Starbucks.users.vo.RequestUser;
import com.example.Starbucks.users.vo.ResponseUser;
import org.apache.coyote.Response;

public interface UserService {
    ResponseUser addUser(RequestUser requestUser);
    ResponseUser getUser(Long id);

}

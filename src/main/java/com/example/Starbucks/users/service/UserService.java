package com.example.Starbucks.users.service;

//import com.example.Starbucks.users.dto.UserInfoDto;
import com.example.Starbucks.users.dto.UserRequestDto;
import com.example.Starbucks.users.model.User;
import com.example.Starbucks.users.vo.RequestUser;
import com.example.Starbucks.users.vo.ResponseUser;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;

public interface UserService {
//    ResponseUser addUser(RequestUser requestUser);
//    ResponseUser getUser(Long id);

    public ResponseEntity<?> signUp(UserRequestDto.SignUp signUp);

    public ResponseEntity<?> login(UserRequestDto.Login login);

    public ResponseEntity<?> reissue(UserRequestDto.Reissue reissue);
    public ResponseEntity<?> logout(UserRequestDto.Logout logout);

    public ResponseEntity<?> authority();

    public ResponseEntity<?> check(String check,String name);

    public ResponseEntity<?> emailCheck(String email);

    public ResponseEntity<?> modifyPassword(UserRequestDto.SignUp signUp);


}

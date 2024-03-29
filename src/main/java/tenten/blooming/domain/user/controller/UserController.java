package tenten.blooming.domain.user.controller;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tenten.blooming.domain.user.dto.LoginResponse;
import tenten.blooming.domain.user.entity.User;
import tenten.blooming.domain.user.dto.UserForm;
import tenten.blooming.domain.user.service.UserService;
import tenten.blooming.global.common.BasicResponse;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<BasicResponse> signup(@RequestBody UserForm dto) {
        User user = userService.join(dto);
        BasicResponse basicResponse = new BasicResponse();


        if (user != null) {
            basicResponse = BasicResponse.builder()
                    .code(HttpStatus.OK.value())
                    .message("회원가입에 성공하였습니다.")
                    .result(user)
                    .build();
        }

        return new ResponseEntity<>(basicResponse, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody UserForm dto) {
        try {
            User user = userService.login(dto.getLoginId(), dto.getPassword());

            LoginResponse.LoginResult loginResult = new LoginResponse.LoginResult();

            Long activeGoalId = userService.getActiveGoalIdByLoginId(dto.getLoginId());

            loginResult.setLoginId(user.getLoginId());
            loginResult.setPassword(user.getPassword());
            loginResult.setNickname(user.getNickname());
            if (userService.hasGoal(dto.getLoginId())) {
                loginResult.setHasGoal(Boolean.TRUE);
            } else {
                loginResult.setHasGoal(Boolean.FALSE);
            }
            loginResult.setCreatedAt(user.getCreatedAt());
            loginResult.setUserId(user.getUserId());
            loginResult.setActiveGoalId(activeGoalId);

            LoginResponse loginResponse = LoginResponse.builder()
                    .code(HttpStatus.OK.value())
                    .message("로그인에 성공했습니다.")
                    .result(loginResult)
                    .build();
            return new ResponseEntity<>(loginResponse, HttpStatus.OK);
        } catch (DataIntegrityViolationException e) {
            LoginResponse loginResponse = LoginResponse.builder()
                    .code(HttpStatus.BAD_REQUEST.value())
                    .message("로그인에 실패했습니다.")
                    .result(null)
                    .build();

            return new ResponseEntity<>(loginResponse, HttpStatus.BAD_REQUEST);
        }
    }
}

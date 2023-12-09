package com.example.modelcontroller.controller;

import com.example.core.dto.LoginDto;
import com.example.core.dto.LoginResponse;
import com.example.modelcontroller.service.UserLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserLoginController {
    private final UserLoginService userLoginService;
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(@RequestBody LoginDto loginDTO) {
        return userLoginService.loginUser(loginDTO);
    }

}

package com.example.deliveryapi.controller;

import com.example.core.dto.LoginDto;
import com.example.core.dto.LoginResponse;
import com.example.deliveryapi.service.UserLoginService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
        Optional<LoginResponse> loginResponse = userLoginService.loginSuccessGenerateToken(loginDTO);
        return loginResponse.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }

}

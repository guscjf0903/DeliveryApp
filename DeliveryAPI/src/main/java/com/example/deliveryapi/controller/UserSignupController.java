package com.example.deliveryapi.controller;

import com.example.core.dto.SignupDto;
import com.example.deliveryapi.service.UserSignupService;

import jakarta.validation.Valid;
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
public class UserSignupController {
    private final UserSignupService userSignupService;

    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@RequestBody @Valid SignupDto signupDTO) {
        try {
            userSignupService.registerUser(signupDTO);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}

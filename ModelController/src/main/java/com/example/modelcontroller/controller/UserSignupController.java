package com.example.modelcontroller.controller;

import com.example.core.dto.SignupDto;
import com.example.modelcontroller.service.UserSignupService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
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
    public HttpStatus registerUser(@RequestBody @Valid SignupDto signupDTO) {
        try {
            userSignupService.registerUser(signupDTO);
        } catch (Exception e) {
            return HttpStatus.BAD_REQUEST;
        }
        return HttpStatus.OK;
    }
}

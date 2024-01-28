package com.example.deliveryui.controller;

import com.example.core.dto.LoginDto;
import com.example.core.dto.LoginResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserLoginViewController {
    private final RestTemplate restTemplate = new RestTemplate();
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/login")
    public String showLoginForm() {
        return "login_form";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<LoginResponse> loginUser(@RequestBody @Valid LoginDto loginDTO, @Value("${api.url}") String url) {
        ResponseEntity<LoginResponse> loginStatus = restTemplate.postForEntity(url + "/user/login", loginDTO, LoginResponse.class);
        return loginStatus;
    }


}

package com.example.viewcontroller.controller;

import com.example.core.dto.LoginDto;
import com.example.core.dto.LoginResponse;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserLoginViewController {
    private final RestTemplate restTemplate = new RestTemplate();
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("loginDTO", new LoginDto());
        return "login_form";
    }

    @PostMapping("/login")
    public String loginUser(LoginDto loginDTO, Model model) {
        ResponseEntity<LoginResponse> loginStatus = null;
        try{
            loginStatus = restTemplate.postForEntity("http://localhost:7777/user/login", loginDTO, LoginResponse.class);
        } catch (Exception e) {
            logger.error("로그인 중 오류 발생", e);
            return "login_fail";
        }
        if(loginStatus.getStatusCode() == HttpStatus.OK) {
            model.addAttribute("loginId", Objects.requireNonNull(loginStatus.getBody()).getLoginId());

            return "login_success";
        }
        else {
            return "login_fail";
        }
    }


}

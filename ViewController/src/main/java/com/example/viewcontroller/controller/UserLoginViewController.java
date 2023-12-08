package com.example.viewcontroller.controller;

import com.example.core.dto.LoginDto;
import com.example.core.dto.LoginResponse;
import lombok.RequiredArgsConstructor;
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
    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("loginDTO", new LoginDto());
        return "login_form";
    }

    @PostMapping("/login")
    public String loginUser(LoginDto loginDTO) {
        ResponseEntity<LoginResponse> loginStatus;
        try{
            loginStatus = restTemplate.postForObject("http://localhost:7777/user/login", loginDTO, ResponseEntity.class);
        } catch (Exception e) {
            return "login_fail";
        }
        if(loginStatus.getStatusCode() == HttpStatus.OK && loginDTO.isUser()) {
            return "redirect:/user/menu";
        } else if(loginStatus.getStatusCode() == HttpStatus.OK && loginDTO.isStore()) {
            return "redirect:/store/menu";
        }
        else {
            return "login_fail";
        }
    }



}

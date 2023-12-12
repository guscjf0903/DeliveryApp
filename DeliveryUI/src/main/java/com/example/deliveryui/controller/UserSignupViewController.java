package com.example.deliveryui.controller;

import com.example.core.dto.SignupDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserSignupViewController {
    private final RestTemplate restTemplate = new RestTemplate();
    @GetMapping("/signup")
    public String showSignUpForm(Model model) {
        model.addAttribute("signupDTO", new SignupDto());
        return "signup_form";
    }
    @PostMapping("/signup")
    public String registerUser(@ModelAttribute SignupDto signupDTO) {
        ResponseEntity<String> signupStatus = restTemplate.postForEntity("http://localhost:7777/user/signup", signupDTO, String.class);
        if(signupStatus.getStatusCode() == HttpStatus.OK) {
            return "signup_success";
        } else {
            return "signup_fail";
        }
    }



}

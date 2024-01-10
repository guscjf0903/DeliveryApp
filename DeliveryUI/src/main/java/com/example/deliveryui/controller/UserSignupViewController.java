package com.example.deliveryui.controller;

import com.example.core.dto.SignupDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
public class UserSignupViewController {
    private final RestTemplate restTemplate = new RestTemplate();
    @GetMapping("/signup")
    public String showSignUpForm() {
        return "html/signup_form";
    }
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ResponseEntity<String> registerUser(@RequestBody @Valid SignupDto signupDTO, @Value("${api.url}") String url) {
        ResponseEntity<String> signupStatus = restTemplate.postForEntity( url + "/user/signup", signupDTO, String.class);

        return signupStatus;
    }
}

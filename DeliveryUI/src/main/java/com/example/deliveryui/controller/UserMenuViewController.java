package com.example.deliveryui.controller;

import com.example.core.dto.MenuDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

@Controller
@RequiredArgsConstructor
@RequestMapping("/menu")
public class UserMenuViewController {
    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/add")
    public String showMenuForm() {
        return "/html/add-menu";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<String> addMenu(@RequestBody @Valid MenuDto menuDTO, @Value("${api.url}") String url) {
        ResponseEntity<String> menuAddHttpStatus = restTemplate.postForEntity( url + "/menu/add", menuDTO, String.class);

        return menuAddHttpStatus;
    }


}

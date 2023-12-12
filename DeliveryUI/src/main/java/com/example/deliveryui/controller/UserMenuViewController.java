package com.example.deliveryui.controller;

import com.example.core.dto.MenuDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@Controller
@RequiredArgsConstructor
@RequestMapping("/menu")
public class UserMenuViewController {
    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/add")
    public String showMenuForm(Model model) {
        model.addAttribute("menuDTO", new MenuDto());
        return "add-menu";
    }

    @PostMapping("/add")
    public String addMenu(@ModelAttribute MenuDto menuDTO) {
        ResponseEntity menuAddHttpStatus = restTemplate.postForEntity("http://localhost:7777/menu/add", menuDTO, ResponseEntity.class);

        if(menuAddHttpStatus.getStatusCode() == HttpStatus.OK) {
            return "menuAdd_success";
        }
        else {
            return "menuAdd_fail";
        }
    }


}

package com.example.viewcontroller.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/menu")
public class UserMenuViewController {
    @GetMapping("/menu")
    public String showMenuForm(Model model) {

        return "user_menu";
    }


}

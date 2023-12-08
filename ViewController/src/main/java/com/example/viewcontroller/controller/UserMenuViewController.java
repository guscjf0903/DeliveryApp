package com.example.viewcontroller.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserMenuViewController {
    @GetMapping("/menu")
    public String showMenuForm(HttpServletRequest request) {
        HttpSession session = request.getSession(); // 새로운 세션 생성
        System.out.println("test");
        System.out.println(session.getAttribute("serialId").toString());
        return "user_menu";
    }


}

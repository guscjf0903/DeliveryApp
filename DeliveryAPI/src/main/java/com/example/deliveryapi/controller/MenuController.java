package com.example.deliveryapi.controller;

import com.example.core.dto.MenuDto;
import com.example.deliveryapi.service.MenuService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/menu")
@RequiredArgsConstructor
public class MenuController {
    private final MenuService menuService;

    @PostMapping("/add")
    public ResponseEntity addMenu(@RequestBody @Valid MenuDto menuDTO) {
        ResponseEntity menuAddResponse;
        try {
            menuAddResponse = menuService.addMenu(menuDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return menuAddResponse;
    }

}

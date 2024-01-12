package com.example.deliveryapi.controller;

import com.example.core.dto.MenuDto;
import com.example.deliveryapi.service.MenuService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/menu")
@RequiredArgsConstructor
public class MenuController {
    private final MenuService menuService;

    @PostMapping("/add")
    public ResponseEntity<String> addMenu(@RequestBody MenuDto menuDTO) {
        try {
            menuService.addMenu(menuDTO);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/getMenuData")
    public ResponseEntity<?> getMenuData(@RequestParam Long loginId) {
        try {
            return ResponseEntity.ok(menuService.getMenuData(loginId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/deleteMenu")
    public ResponseEntity<?> deleteMenu(@RequestParam Long loginId, @RequestParam String menuName) {
        try {
            menuService.deleteMenu(loginId, menuName);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}

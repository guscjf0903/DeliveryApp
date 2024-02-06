package com.example.deliveryapi.controller;

import com.example.core.dto.MenuData;
import com.example.core.dto.MenuDto;
import com.example.deliveryapi.service.MenuService;
import java.util.HashMap;
import java.util.List;
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
        menuService.addMenu(menuDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getMenuData")
    public ResponseEntity<?> getMenuData(@RequestParam Long loginId) {
        return ResponseEntity.ok(menuService.getMenuData(loginId));

    }

    @DeleteMapping("/deleteMenu")
    public ResponseEntity<?> deleteMenu(@RequestParam Long loginId, @RequestParam String menuName) {
        menuService.deleteMenu(loginId, menuName);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getAllMenuData")
    public ResponseEntity<?> getAllMenuData() {
        HashMap<String, List<MenuData>> menuData = menuService.getAllMenuData();
        return ResponseEntity.ok(menuData);
    }

}

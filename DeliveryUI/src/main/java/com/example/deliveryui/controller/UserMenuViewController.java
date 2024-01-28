package com.example.deliveryui.controller;

import com.example.core.dto.MenuDto;
import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
@RequiredArgsConstructor
@RequestMapping("/menu")
public class UserMenuViewController {
    private final RestTemplate restTemplate = new RestTemplate();

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String showMenuForm() {
        return "add-menu";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<String> addMenu(@RequestBody @Valid MenuDto menuDTO, @Value("${api.url}") String url) {
        ResponseEntity<String> menuAddHttpStatus = restTemplate.postForEntity( url + "/menu/add", menuDTO, String.class);

        return menuAddHttpStatus;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String showEditMenuForm() {
        return "edit-menu";
    }

    @RequestMapping(value = "/getMenuData", method = RequestMethod.GET)
    public ResponseEntity<List> getMenuData(@RequestParam Long loginId, @Value("${api.url}") String url) {
        System.out.println("loginId: " + loginId);
        ResponseEntity<List> menuAddHttpStatus = restTemplate.getForEntity( url + "/menu/getMenuData?loginId=" + loginId, List.class);

        return menuAddHttpStatus;
    }

    @DeleteMapping(value = "/deleteMenu")
    public ResponseEntity<String> deleteMenu(@RequestParam Long loginId, @RequestParam String menuName,@Value("${api.url}") String url) {
        ResponseEntity<String> menuAddHttpStatus = restTemplate.exchange(url + "/menu/deleteMenu?loginId=" + loginId + "&menuName=" + menuName, HttpMethod.DELETE, null, String.class);
        return menuAddHttpStatus;
    }

    @GetMapping("/getAllMenuData")
    public ResponseEntity<?> getAllMenuData(@Value("${api.url}") String url) {
        ResponseEntity<?> menuAddHttpStatus = restTemplate.getForEntity( url + "/menu/getAllMenuData", HashMap.class);

        return menuAddHttpStatus;
    }


}

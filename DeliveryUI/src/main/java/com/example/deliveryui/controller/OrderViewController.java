package com.example.deliveryui.controller;

import com.example.core.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

@Controller
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderViewController {
    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/add")
    public String showOrderForm() {
        return "html_order";
    }
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<String> addOrder(@RequestBody OrderDto orderDTO) {
        ResponseEntity<String> orderAddStatus = restTemplate.postForEntity("http://localhost:7777/order/add", orderDTO, String.class);

        return orderAddStatus;
    }
}

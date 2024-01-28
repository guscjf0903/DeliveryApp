package com.example.deliveryui.controller;

import com.example.core.dto.OrderDto;
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
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderViewController {
    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/add")
    public String showOrderForm() {
        return "html_order";
    }
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<String> addOrder(@RequestBody @Valid OrderDto orderDTO, @Value("${api.url}") String url) {
        ResponseEntity<String> orderAddStatus = restTemplate.postForEntity(url + "/order/add", orderDTO, String.class);

        return orderAddStatus;
    }

    @RequestMapping(value = "/store/list", method = RequestMethod.GET)
    public ResponseEntity<String> addOrder(@Value("${api.url}") String url) {
        ResponseEntity<String> StoreList = restTemplate.getForEntity(url + "/user/store/list", String.class);

        return StoreList;
    }
}

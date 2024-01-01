package com.example.deliveryui.controller;

import com.example.core.dto.SalesDto;
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
@RequestMapping("/sales")
public class SalesViewController {
    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/view")
    public String showSalesForm() {
        return "sales_form";
    }

    @RequestMapping(value = "/date", method = RequestMethod.POST)
    public ResponseEntity<String> getSalesByDate(@RequestBody SalesDto salesDTO, @Value("${api.url}") String url) {
        ResponseEntity<String> salesByDate = restTemplate.postForEntity(url + "/sales/date", salesDTO,String.class);
        System.out.println(salesByDate.getBody());
        return salesByDate;
    }
}

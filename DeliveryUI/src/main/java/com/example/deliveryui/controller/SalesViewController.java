package com.example.deliveryui.controller;

import com.example.core.dto.SalesDto;
import java.util.Collections;
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
        return "html/sales_form";
    }

    @RequestMapping(value = "/date", method = RequestMethod.POST)
    public ResponseEntity<Object> getSalesByDate(@RequestBody SalesDto salesDTO, @Value("${api.url}") String url) {
        try {
            ResponseEntity<Object> salesByDate = restTemplate.postForEntity(url + "/sales/date", salesDTO, Object.class);
            return salesByDate;
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("error", "매출 확인에 실패했습니다." + e.getMessage()));
        }

    }
}

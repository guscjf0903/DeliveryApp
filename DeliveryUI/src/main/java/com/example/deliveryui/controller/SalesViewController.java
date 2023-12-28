package com.example.deliveryui.controller;

import com.example.core.dto.SalesDto;
import com.example.core.dto.SalesResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Controller
@RequiredArgsConstructor
@RequestMapping("/sales")
public class SalesViewController {
    private final RestTemplate restTemplate = new RestTemplate();
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/view")
    public String showSalesView() {
        return "sales_view";
    }
    @RequestMapping(value = "/view/total", method = RequestMethod.POST)
    public ResponseEntity<SalesResponse> showTotalSalesView(@RequestBody SalesDto salesDTO) {
        ResponseEntity<SalesResponse> totalSales = restTemplate.postForEntity("http://localhost:7777/sales/total", salesDTO, SalesResponse.class);
        //테스트용
        System.out.println(totalSales.getBody().getUserOrStoreName());
        System.out.println(totalSales.getBody().getMonth());
        System.out.println(totalSales.getBody().getSales());

        return totalSales;
    }


}

package com.example.deliveryui.controller;

import java.time.LocalDate;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

    @RequestMapping(value = "/date", method = RequestMethod.GET)
    public ResponseEntity<Object> getSalesByDate(@RequestParam Long loginId, @RequestParam String userType, @RequestParam  LocalDate startDate, @RequestParam LocalDate endDate, @Value("${api.url}") String url) {
        try {
            return restTemplate.getForEntity(url + "/sales/date?loginId=" + loginId + "&userType="+userType + "&startDate="+ startDate+"&endDate="+endDate, Object.class);
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(Collections.singletonMap("error", "매출 확인에 실패했습니다." + e.getMessage()));
        }
    }

    @RequestMapping(value = "/time", method = RequestMethod.GET)
    public ResponseEntity<Object> getSalesByTime(@RequestParam Long loginId, @RequestParam LocalDate startDate ,@RequestParam LocalDate endDate, @RequestParam String timeType, @Value("${api.url}") String url) {
        try {
            return restTemplate.getForEntity(url + "/sales/time?loginId=" + loginId + "&startDate=" + startDate + "&endDate=" + endDate + "&timeType=" + timeType, Object.class);
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(Collections.singletonMap("error", "매출 확인에 실패했습니다." + e.getMessage()));
        }
    }
}

package com.example.deliveryapi.controller;

import com.example.core.dto.SalesDto;
import com.example.deliveryapi.service.SalesService;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sales")
@RequiredArgsConstructor
public class SalesController {
    private final SalesService salesService;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/date")
    public ResponseEntity<?> getSalesByDate(@RequestParam Long loginId, @RequestParam String userType, @RequestParam  LocalDate startDate, @RequestParam LocalDate endDate) {
        BigDecimal salesTotal = salesService.getSalesByDate(loginId, userType, startDate, endDate);
        // 성공적인 응답 반환
        return ResponseEntity.ok().body(Collections.singletonMap("salesTotal", salesTotal));
    }

    @GetMapping("/time")
    public ResponseEntity<?> getSalesByTime(@RequestParam Long loginId, @RequestParam LocalDate startDate,@RequestParam LocalDate endDate, @RequestParam String timeType) {
        BigDecimal salesTotal = salesService.getSalesByTime(loginId, startDate, endDate, timeType);
        return ResponseEntity.ok().body(Collections.singletonMap("salesTotal", salesTotal));
    }
}

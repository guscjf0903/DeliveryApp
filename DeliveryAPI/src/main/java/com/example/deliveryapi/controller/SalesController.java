package com.example.deliveryapi.controller;

import com.example.core.dto.SalesDto;
import com.example.deliveryapi.service.SalesService;
import java.math.BigDecimal;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sales")
@RequiredArgsConstructor
public class SalesController {
    private final SalesService salesService;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @PostMapping("/date")
    public ResponseEntity<?> getSalesByDate(@RequestBody SalesDto salesDTO) {
        BigDecimal salesTotal = salesService.getSalesByDate(salesDTO);
        // 성공적인 응답 반환
        return ResponseEntity.ok().body(Collections.singletonMap("salesTotal", salesTotal));
    }
}

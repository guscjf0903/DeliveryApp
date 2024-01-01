package com.example.deliveryapi.controller;

import com.example.core.dto.SalesDto;
import com.example.deliveryapi.service.SalesService;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

    @PostMapping("/date")
    public ResponseEntity<Map<String, Object>> getSalesByDate(@RequestBody SalesDto salesDTO) {
        try {
            int salesTotal = salesService.getSalesByDate(salesDTO);
            Map<String, Object> response = new HashMap<>();
            response.put("salesTotal", salesTotal);

            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }
}

package com.example.deliveryapi.controller;

import com.example.core.dto.SalesDto;
import com.example.core.dto.SalesResponse;
import com.example.deliveryapi.service.SalesService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/sales")
@RequiredArgsConstructor
public class SalesController {
    private final SalesService salesService;

    @PostMapping("/total")
    public ResponseEntity<SalesResponse> getTotalSales(@RequestBody SalesDto salesDto){
        Optional<SalesResponse> salesResponse = salesService.getTotalSales(salesDto);
        return salesResponse.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }

}

package com.example.core.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SalesDto {
    private boolean isStore;
    private boolean isUser;
    private LocalDate startDate;
    private LocalDate endDate;
}

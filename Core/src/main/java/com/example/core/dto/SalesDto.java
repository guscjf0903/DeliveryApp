package com.example.core.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AccessLevel;
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

    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Long loginId;
}

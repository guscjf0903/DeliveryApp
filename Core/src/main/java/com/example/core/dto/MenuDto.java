package com.example.core.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MenuDto {
    private String menuName;
    private int menuPrice;
    private String menuCategory;
    private int LoginId;
}

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
    @NotBlank(message = "메뉴 이름을 입력해주세요.")
    private String menuName;
    private int menuPrice;
    private String menuCategory;
    private int loginId;
}

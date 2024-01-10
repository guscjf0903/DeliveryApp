package com.example.core.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import java.math.BigDecimal;
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
    @Digits(integer = 10, fraction = 0, message = "숫자만 입력해주세요.")
    private BigDecimal menuPrice;

    private String menuCategory;
    private Long loginId;
}

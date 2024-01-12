package com.example.core.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import lombok.Getter;

@Getter
public class MenuData {
    private final String menuName;
    private final BigDecimal menuPrice;

    @JsonCreator
    public MenuData(@JsonProperty("menuName") String menuName, @JsonProperty("menuPrice") BigDecimal menuPrice) {
        this.menuName = menuName;
        this.menuPrice = menuPrice;
    }
}

package com.example.core.dto;

import jakarta.validation.constraints.NotBlank;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private String storeName;
    private List<MenuOrderDTO> menuOrders;
    private int LoginId;

    @Getter
    @Setter
    public static class MenuOrderDTO {
        @NotBlank(message = "메뉴 이름을 입력해주세요.")
        private String menuName;
        @NotBlank(message = "수량을 입력해주세요.")
        private int quantity;
    }
}


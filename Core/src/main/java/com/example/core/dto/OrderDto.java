package com.example.core.dto;

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
        private String menuName;
        private int quantity;
    }
}


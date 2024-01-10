package com.example.deliveryapi.entity;

import com.example.core.dto.MenuDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "menus", schema = "delivery_schema")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MenuAddDataEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_id")
    private Long menuId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserSignupDataEntity user;

    @Column(name = "menu_name", nullable = false)
    private String menuName;

    @Column(name = "menu_price", nullable = false, precision = 10, scale = 0)
    private BigDecimal menuPrice;

    @Column(name = "menu_category")
    private String menuCategory;

    public MenuAddDataEntity(String menuName, BigDecimal menuPrice, String menuCategory) {
        this.menuName = menuName;
        this.menuPrice = menuPrice;
        this.menuCategory = menuCategory;
    }

    public static MenuAddDataEntity of(MenuDto menuDto) {
        return new MenuAddDataEntity(menuDto.getMenuName(), menuDto.getMenuPrice(), menuDto.getMenuCategory());
    }


}

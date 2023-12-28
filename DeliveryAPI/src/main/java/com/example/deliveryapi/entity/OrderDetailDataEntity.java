package com.example.deliveryapi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "order_details", schema = "delivery_schema")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderDetailDataEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "detail_order_id")
    private Long orderDetailId;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private OrderDataEntity order;

    @OneToOne
    @JoinColumn(name = "menu_id", nullable = false)
    private MenuAddDataEntity menuId;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "menu_price", nullable = false)
    private int menuPrice;

    public OrderDetailDataEntity(OrderDataEntity order,MenuAddDataEntity menuId, int quantity, int menuPrice) {
        this.order = order;
        this.menuId = menuId;
        this.quantity = quantity;
        this.menuPrice = menuPrice;
    }
}

package com.example.deliveryapi.model;

import com.example.deliveryapi.entity.OrderDataEntity;
import java.time.LocalDateTime;
import java.util.List;

public interface CustomRepository {
    List<OrderDataEntity> findByStoreOrderDatetimeBetween(long userId, LocalDateTime start, LocalDateTime end);
    List<OrderDataEntity> findByUserOrderDatetimeBetween(long userId, LocalDateTime start, LocalDateTime end);
}

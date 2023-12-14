package com.example.deliveryapi.model;

import com.example.deliveryapi.entity.OrderDetailDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetailDataEntity, Integer> {
}

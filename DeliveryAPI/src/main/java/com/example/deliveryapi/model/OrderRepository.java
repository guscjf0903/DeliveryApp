package com.example.deliveryapi.model;

import com.example.deliveryapi.entity.OrderDataEntity;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderDataEntity, Integer> {
    List<OrderDataEntity> findByUserUserIdAndOrderDatetimeBetween(long userId, LocalDate start, LocalDate end);
    List<OrderDataEntity> findByStoreUserIdAndOrderDatetimeBetween(long storeId, LocalDate start, LocalDate end);
}


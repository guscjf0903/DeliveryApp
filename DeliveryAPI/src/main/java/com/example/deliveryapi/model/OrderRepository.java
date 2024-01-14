package com.example.deliveryapi.model;

import com.example.deliveryapi.entity.OrderDataEntity;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderDataEntity, Long> {
    List<OrderDataEntity> findByUserUserIdAndOrderDatetimeBetween(long userId, LocalDateTime start, LocalDateTime end);
    List<OrderDataEntity> findByStoreUserIdAndOrderDatetimeBetween(long storeId, LocalDateTime start, LocalDateTime end);

    @Query("SELECT o FROM OrderDataEntity o WHERE o.store.userId = ?1 AND o.orderDatetime BETWEEN ?2 AND ?3")
    List<OrderDataEntity> findSaleByTime(long userId, LocalDateTime start, LocalDateTime end);
}


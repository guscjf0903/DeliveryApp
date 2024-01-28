package com.example.deliveryapi.model;

import com.example.deliveryapi.entity.OrderDataEntity;
import java.math.BigDecimal;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderDataEntity, Long> {
    List<OrderDataEntity> findByUserUserIdAndOrderDatetimeBetween(long userId, LocalDateTime start, LocalDateTime end);
    List<OrderDataEntity> findByStoreUserIdAndOrderDatetimeBetween(long storeId, LocalDateTime start, LocalDateTime end);

    @Query("SELECT SUM(o.totalPrice) FROM OrderDataEntity o " +
            "WHERE o.store.userId = :userId " +
            "AND o.orderDatetime BETWEEN :startDateTime AND :endDateTime " +
            "AND CAST(DATE_PART('hour', CAST(o.orderDatetime AS timestamp)) AS INTEGER) BETWEEN CAST(DATE_PART('hour', CAST(:startDateTime AS timestamp)) AS INTEGER) AND CAST(DATE_PART('hour', CAST(:endDateTime AS timestamp)) AS INTEGER)")
    BigDecimal findSaleByTime(@Param("userId") long userId,
                              @Param("startDateTime") LocalDateTime startDateTime,
                              @Param("endDateTime") LocalDateTime endDateTime);





}


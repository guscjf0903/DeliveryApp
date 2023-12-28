package com.example.deliveryapi.model;

import com.example.deliveryapi.entity.OrderDataEntity;
import java.time.Month;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderDataEntity, Integer> {
    @Query("SELECT EXTRACT(MONTH FROM o.orderDate) FROM OrderDataEntity o WHERE o.orderId = :orderId")
    int findMonthByOrderId(@Param("orderId") Long orderId);
}

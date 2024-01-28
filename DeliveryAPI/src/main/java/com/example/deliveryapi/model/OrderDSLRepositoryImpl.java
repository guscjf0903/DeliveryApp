package com.example.deliveryapi.model;

import static com.example.deliveryapi.entity.QOrderDataEntity.orderDataEntity;

import com.example.deliveryapi.entity.OrderDataEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;


@Repository
@AllArgsConstructor
public class OrderDSLRepositoryImpl implements CustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<OrderDataEntity> findByUserOrderDatetimeBetween(long userId, LocalDateTime start, LocalDateTime end) {
        return jpaQueryFactory.selectFrom(orderDataEntity)
                .where(orderDataEntity.user.userId.eq(userId)
                        .and(orderDataEntity.orderDatetime.between(start, end))).fetch();
    }

    @Override
    public List<OrderDataEntity> findByStoreOrderDatetimeBetween(long userId, LocalDateTime start, LocalDateTime end) {
        return jpaQueryFactory.selectFrom(orderDataEntity)
                .where(orderDataEntity.store.userId.eq(userId)
                        .and(orderDataEntity.orderDatetime.between(start, end))).fetch();
    }

    public BigDecimal findSaleByTime(long userId, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return jpaQueryFactory.select(orderDataEntity.totalPrice.sum())
                .from(orderDataEntity)
                .where(orderDataEntity.store.userId.eq(userId)
                        .and(orderDataEntity.orderDatetime.between(startDateTime, endDateTime))
                        .and(orderDataEntity.orderDatetime.hour().between(startDateTime.getHour(), endDateTime.getHour())))
                .fetchOne();
    }
}

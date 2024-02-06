package com.example.deliveryapi.model;

import com.example.deliveryapi.entity.OrderDataEntity;
import com.example.deliveryapi.entity.UserSignupDataEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class OrderCriteriaRepository implements CustomRepository {
    @PersistenceContext
    EntityManager entityManager;
    @Override
    public List<OrderDataEntity> findByStoreOrderDatetimeBetween(long userId, LocalDateTime start, LocalDateTime end) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<OrderDataEntity> cq = criteriaBuilder.createQuery(OrderDataEntity.class);
        Root<OrderDataEntity> orderDataEntityRoot = cq.from(OrderDataEntity.class);

        Predicate predicate = criteriaBuilder.and(
                criteriaBuilder.equal(orderDataEntityRoot.get("store").get("userId"), userId),
                criteriaBuilder.between(orderDataEntityRoot.get("orderDatetime"), start, end)
        );
        cq.select(orderDataEntityRoot).where(predicate);

        return entityManager.createQuery(cq).getResultList();
    }

    @Override
    public List<OrderDataEntity> findByUserOrderDatetimeBetween(long userId, LocalDateTime start, LocalDateTime end) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<OrderDataEntity> cq = criteriaBuilder.createQuery(OrderDataEntity.class);
        Root<OrderDataEntity> orderDataEntityRoot = cq.from(OrderDataEntity.class);

        Predicate predicate = criteriaBuilder.and(
                criteriaBuilder.equal(orderDataEntityRoot.get("user").get("userId"), userId),
                criteriaBuilder.between(orderDataEntityRoot.get("orderDatetime"), start, end)
        );
        cq.select(orderDataEntityRoot).where(predicate);

        return entityManager.createQuery(cq).getResultList();
    }

    public BigDecimal findSaleByTime(long userId, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        LocalDate startDate = startDateTime.toLocalDate();
        LocalDate endDate = endDateTime.toLocalDate();
        int startHour = startDateTime.getHour();
        int endHour = endDateTime.getHour();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<BigDecimal> criteriaQuery = criteriaBuilder.createQuery(BigDecimal.class);

        Root<OrderDataEntity> orderRoot = criteriaQuery.from(OrderDataEntity.class);
        Join<OrderDataEntity, UserSignupDataEntity> storeJoin = orderRoot.join("store");

        criteriaQuery.select(criteriaBuilder.sum(orderRoot.get("totalPrice")));

        Predicate userPredicate = criteriaBuilder.equal(storeJoin.get("userId"), userId);
        Expression<Integer> hourExpression = criteriaBuilder.function(
                "EXTRACT",
                Integer.class,
                criteriaBuilder.literal("hour"),
                orderRoot.get("orderDatetime")
        );
        Predicate hourPredicate = criteriaBuilder.between(hourExpression, startHour, endHour);


        Predicate datePredicate = criteriaBuilder.between(orderRoot.get("orderDatetime"), startDate, endDate);

        criteriaQuery.where(criteriaBuilder.and(userPredicate, hourPredicate, datePredicate));


        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }

}

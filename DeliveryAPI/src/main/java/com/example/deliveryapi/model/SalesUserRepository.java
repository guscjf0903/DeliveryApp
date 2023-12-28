package com.example.deliveryapi.model;

import com.example.deliveryapi.entity.SalesStoreEntity;
import com.example.deliveryapi.entity.SalesUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesUserRepository extends JpaRepository<SalesUserEntity, Integer> {
    SalesUserEntity findByUserUserIdAndMonth(Long userId, int month);

}

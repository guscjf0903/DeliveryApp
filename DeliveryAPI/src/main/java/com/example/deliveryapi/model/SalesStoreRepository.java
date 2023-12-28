package com.example.deliveryapi.model;

import com.example.deliveryapi.entity.MenuAddDataEntity;
import com.example.deliveryapi.entity.SalesStoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesStoreRepository  extends JpaRepository<SalesStoreEntity, Integer> {
    SalesStoreEntity findByStoreUserIdAndMonth(Long userId, int month);

}

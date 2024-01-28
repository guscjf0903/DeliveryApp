package com.example.deliveryapi.model;

import com.example.deliveryapi.entity.LoginDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<LoginDataEntity, Long> {
    boolean existsByUserUserId(long loginId);

    void deleteByUserUserId(long loginId);
}

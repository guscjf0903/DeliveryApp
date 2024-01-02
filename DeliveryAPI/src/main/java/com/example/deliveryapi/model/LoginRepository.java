package com.example.deliveryapi.model;

import com.example.deliveryapi.entity.LoginDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<LoginDataEntity, Integer> {
    boolean existsByUserUserId(long loginId);

    void deleteByuserUserId(long loginId);
}

package com.example.deliveryapi.model;

import com.example.deliveryapi.entity.UserSignupDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserSignupDataEntity, Integer> {
    UserSignupDataEntity findByUserName(String username);
    UserSignupDataEntity findByCompanyName(String companyName);
}

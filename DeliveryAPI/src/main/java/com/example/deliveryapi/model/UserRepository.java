package com.example.deliveryapi.model;

import com.example.deliveryapi.entity.UserSignupData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserSignupData, Integer> {
    UserSignupData findByUserName(String username);
}

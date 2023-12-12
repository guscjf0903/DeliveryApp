package com.example.deliveryapi.model;

import com.example.deliveryapi.entity.LoginData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<LoginData, Integer> {
}

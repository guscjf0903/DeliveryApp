package com.example.modelcontroller.model;

import com.example.modelcontroller.entity.Login;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepository extends JpaRepository<Login, Integer> {
}

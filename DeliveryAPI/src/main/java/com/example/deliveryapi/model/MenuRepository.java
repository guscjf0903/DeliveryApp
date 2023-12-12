package com.example.deliveryapi.model;

import com.example.deliveryapi.entity.MenuAddData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends JpaRepository<MenuAddData, Integer> {

}

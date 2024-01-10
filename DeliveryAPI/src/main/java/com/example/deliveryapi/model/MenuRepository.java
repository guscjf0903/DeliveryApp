package com.example.deliveryapi.model;

import com.example.deliveryapi.entity.MenuAddDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends JpaRepository<MenuAddDataEntity, Long> {
    public MenuAddDataEntity findByMenuId(Long menuId);

}

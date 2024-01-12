package com.example.deliveryapi.model;

import com.example.deliveryapi.entity.MenuAddDataEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends JpaRepository<MenuAddDataEntity, Long> {
    MenuAddDataEntity findByMenuId(Long menuId);
}

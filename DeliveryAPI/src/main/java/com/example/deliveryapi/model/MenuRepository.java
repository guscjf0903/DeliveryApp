package com.example.deliveryapi.model;

import com.example.deliveryapi.entity.MenuAddDataEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface MenuRepository extends JpaRepository<MenuAddDataEntity, Long> {
    MenuAddDataEntity findByMenuId(Long menuId);

    @Override
    @Query("UPDATE MenuAddDataEntity m SET m.deleted = true WHERE m.menuId = ?1")
    @Modifying
    @Transactional
    void deleteById(Long menuId);
}

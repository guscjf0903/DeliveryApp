package com.example.deliveryapi.service;

import com.example.deliveryapi.entity.MenuAddDataEntity;
import com.example.deliveryapi.entity.OrderDataEntity;
import com.example.deliveryapi.entity.OrderDetailDataEntity;
import com.example.deliveryapi.entity.UserSignupDataEntity;
import com.example.deliveryapi.exception.OrderFailedException;
import com.example.deliveryapi.model.MenuRepository;
import com.example.deliveryapi.model.OrderDetailRepository;
import java.util.HashMap;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderDetailService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final OrderDetailRepository orderDetailRepository;
    private final MenuRepository menuRepository;

    @Transactional
    public void addOrderDetail(OrderDataEntity orderDataEntity, HashMap<Long, Integer> menuMap) {
        try {
            setOrderDetail(orderDataEntity, menuMap);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new OrderFailedException("menu add failed");
        }
    }

    private void setOrderDetail(OrderDataEntity orderDataEntity, HashMap<Long, Integer> menuMap) {
        try {
            for (Long menuId : menuMap.keySet()) {
                OrderDetailDataEntity orderDetail = new OrderDetailDataEntity();
                orderDetail.setOrder(orderDataEntity);

                MenuAddDataEntity menu = menuRepository.findByMenuId(menuId);
                orderDetail.setMenuId(menu);

                orderDetail.setQuantity(menuMap.get(menuId));
                orderDetail.setMenuPrice(menu.getMenuPrice());
                orderDetailRepository.save(orderDetail);

                orderDataEntity.getOrderDetail().add(orderDetail); // orderDetail을 order에 넣어줌
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new OrderFailedException("menu add failed");
        }
    }
}

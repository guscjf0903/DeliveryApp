package com.example.deliveryapi.service;

import static com.example.deliveryapi.exception.ErrorCode.ORDERDETAIL_ADD_FAILED;

import com.example.deliveryapi.entity.MenuAddDataEntity;
import com.example.deliveryapi.entity.OrderDataEntity;
import com.example.deliveryapi.entity.OrderDetailDataEntity;
import com.example.deliveryapi.exception.CustomException;
import com.example.deliveryapi.model.MenuRepository;
import com.example.deliveryapi.model.OrderDetailRepository;
import java.util.HashMap;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderDetailService {
    private final OrderDetailRepository orderDetailRepository;
    private final MenuRepository menuRepository;

    @Transactional
    public void addOrderDetail(OrderDataEntity orderDataEntity, HashMap<Long, Integer> menuMap) {
        try {
            setOrderDetail(orderDataEntity, menuMap);
        } catch (Exception e) {
            throw new CustomException(ORDERDETAIL_ADD_FAILED);
        }
    }

    private void setOrderDetail(OrderDataEntity orderDataEntity, HashMap<Long, Integer> menuMap) {
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
    }
}

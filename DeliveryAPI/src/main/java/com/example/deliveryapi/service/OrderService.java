package com.example.deliveryapi.service;

import com.example.core.dto.OrderDto;
import com.example.core.dto.OrderDto.MenuOrderDTO;
import com.example.deliveryapi.entity.LoginDataEntity;
import com.example.deliveryapi.entity.MenuAddDataEntity;
import com.example.deliveryapi.entity.OrderDataEntity;
import com.example.deliveryapi.entity.UserSignupDataEntity;
import com.example.deliveryapi.exception.MenuNotFoundException;
import com.example.deliveryapi.exception.OrderFailedException;
import com.example.deliveryapi.model.MenuRepository;
import com.example.deliveryapi.model.OrderRepository;
import com.example.deliveryapi.model.UserRepository;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final UserLoginService userLoginService;
    private final UserSignupService userSignupService;
    private final UserRepository userRepository;
    private final MenuRepository menuRepository;
    private final OrderRepository orderRepository;
    private final OrderDetailService orderDetailService;
    private final SalesService salesService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Transactional
    public void addOrder(OrderDto orderDTO) {
        try {
            LoginDataEntity login = userLoginService.validateLoginId(orderDTO.getLoginId());
            userSignupService.checkUserPermission(login); // user인지 확인
            UserSignupDataEntity storeData = storeNameCheck(orderDTO); // store 이름 확인 후 storeData 저장
            HashMap<Long, Integer> menuMap = storeMenuCheck(orderDTO, storeData); // menu 이름 확인 후 menuMap에 갯수와 함께 저장
            OrderDataEntity orderDataEntity = saveOrder(login.getUser(), storeData, menuMap); // order 저장

            orderDetailService.addOrderDetail(orderDataEntity, menuMap); // orderDetail관련 로직은 orderDetailService에서 처리
            salesService.setSales(orderDataEntity); // sales 저장
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new OrderFailedException("menu add failed");
        }
    }

    @Transactional
    public OrderDataEntity saveOrder(UserSignupDataEntity user, UserSignupDataEntity store, HashMap<Long, Integer> menuMap) {
        try {
            OrderDataEntity order = new OrderDataEntity();
            order.setUser(user);
            order.setStore(store);
            int totalPrice = menuTotalPrice(menuMap);
            order.setTotalPrice(totalPrice);
            order.setOrderDate(LocalDateTime.now()); // testing

            return orderRepository.save(order);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new OrderFailedException("menu add failed");
        }
    }

    private int menuTotalPrice(HashMap<Long, Integer> menuMap) {
        int totalPrice = 0;
        try {
            for(Long menuId : menuMap.keySet()) {
                MenuAddDataEntity menu = menuRepository.findByMenuId(menuId);
                totalPrice += menu.getMenuPrice() * menuMap.get(menuId);
            }
            return totalPrice;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new OrderFailedException("menu add failed");
        }
    }


    private UserSignupDataEntity storeNameCheck(OrderDto orderDTO) {
        try {
            return userRepository.findByCompanyName(orderDTO.getStoreName());
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new OrderFailedException("menu add failed");
        }
    }

    private HashMap<Long, Integer> storeMenuCheck(OrderDto orderDTO, UserSignupDataEntity storeData) {
        try {
            HashMap<Long, Integer> menuMap = new HashMap<>();
            List<MenuOrderDTO> menuOrders = orderDTO.getMenuOrders();
            List<MenuAddDataEntity> storeMenus = storeData.getMenus();

            for(MenuOrderDTO menuOrder : menuOrders) {
                MenuAddDataEntity matchedMenu = findMenuByName(storeMenus, menuOrder.getMenuName());
                Long menuId = matchedMenu.getMenuId();
                menuMap.put(menuId, menuOrder.getQuantity());
            }

            return menuMap;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new OrderFailedException("menu add failed");
        }
    }

    private MenuAddDataEntity findMenuByName(List<MenuAddDataEntity> storeMenus, String menuName) {
        for(MenuAddDataEntity menu : storeMenus) {
            if(menu.getMenuName().equals(menuName)) {
                return menu;
            }
        }
        throw new MenuNotFoundException("menu not found");
    }


}

package com.example.deliveryapi.service;

import static com.example.deliveryapi.exception.ErrorCode.MENU_FOUND_FAILED;
import static com.example.deliveryapi.exception.ErrorCode.MENU_TOTAL_FETCH_FAILED;
import static com.example.deliveryapi.exception.ErrorCode.NOT_FOUND_STORE;
import static com.example.deliveryapi.exception.ErrorCode.ORDER_ADD_FAILED;

import com.example.core.dto.OrderDto;
import com.example.core.dto.OrderDto.MenuOrderDTO;
import com.example.deliveryapi.entity.LoginDataEntity;
import com.example.deliveryapi.entity.MenuAddDataEntity;
import com.example.deliveryapi.entity.OrderDataEntity;
import com.example.deliveryapi.entity.UserSignupDataEntity;
import com.example.deliveryapi.exception.CustomException;
import com.example.deliveryapi.model.MenuRepository;
import com.example.deliveryapi.model.OrderDSLRepositoryImpl;
import com.example.deliveryapi.model.OrderRepository;
import com.example.deliveryapi.model.UserRepository;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    @Transactional
    public void addOrder(OrderDto orderDTO) {
        LoginDataEntity login = userLoginService.validateLoginId(orderDTO.getLoginId());
        userSignupService.checkUserPermission(login); // user인지 확인
        UserSignupDataEntity storeData = storeNameCheck(orderDTO); // store 이름 확인 후 storeData 저장
        HashMap<Long, Integer> menuMap = storeMenuCheck(orderDTO, storeData); // menu 이름 확인 후 menuMap에 갯수와 함께 저장
        OrderDataEntity orderDataEntity = saveOrder(login.getUser(), storeData, menuMap); // order 저장

        orderDetailService.addOrderDetail(orderDataEntity, menuMap); // orderDetail관련 로직은 orderDetailService에서 처리
    }

    private OrderDataEntity saveOrder(UserSignupDataEntity user, UserSignupDataEntity store, HashMap<Long, Integer> menuMap) {
        try {
            OrderDataEntity order = new OrderDataEntity();
            order.setUser(user);
            order.setStore(store);
            BigDecimal totalPrice = menuTotalPrice(menuMap);
            order.setTotalPrice(totalPrice);

            return orderRepository.save(order);
        } catch (Exception e) {
            throw new CustomException(ORDER_ADD_FAILED);
        }
    }

    private BigDecimal menuTotalPrice(HashMap<Long, Integer> menuMap) {
        BigDecimal totalPrice = BigDecimal.valueOf(0);
        try {
            for(Long menuId : menuMap.keySet()) {
                MenuAddDataEntity menu = menuRepository.findByMenuId(menuId);
                BigDecimal menuQuantity = BigDecimal.valueOf(menuMap.get(menuId));

                totalPrice = totalPrice.add(menu.getMenuPrice().multiply(menuQuantity));
            }
            return totalPrice;
        } catch (Exception e) {
            throw new CustomException(MENU_TOTAL_FETCH_FAILED);
        }
    }


    private UserSignupDataEntity storeNameCheck(OrderDto orderDTO) {
        UserSignupDataEntity storeData = userRepository.findByCompanyName(orderDTO.getStoreName());
        if(storeData == null) {
            throw new CustomException(NOT_FOUND_STORE);
        }

        return storeData;
    }

    private HashMap<Long, Integer> storeMenuCheck(OrderDto orderDTO, UserSignupDataEntity storeData) {
        HashMap<Long, Integer> menuMap = new HashMap<>();
        List<MenuOrderDTO> menuOrders = orderDTO.getMenuOrders();
        List<MenuAddDataEntity> storeMenus = storeData.getMenus();

        for(MenuOrderDTO menuOrder : menuOrders) {
            MenuAddDataEntity matchedMenu = findMenuByName(storeMenus, menuOrder.getMenuName());
            Long menuId = matchedMenu.getMenuId();
            menuMap.put(menuId, menuOrder.getQuantity());
        }

        return menuMap;
    }

    private MenuAddDataEntity findMenuByName(List<MenuAddDataEntity> storeMenus, String menuName) {
        for(MenuAddDataEntity menu : storeMenus) {
            if(menu.getMenuName().equals(menuName)) {
                return menu;
            }
        }
        throw new CustomException(MENU_FOUND_FAILED);
    }

}

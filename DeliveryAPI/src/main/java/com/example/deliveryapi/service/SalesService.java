package com.example.deliveryapi.service;

import com.example.core.dto.SalesDto;
import com.example.deliveryapi.entity.LoginDataEntity;
import com.example.deliveryapi.entity.OrderDataEntity;
import com.example.deliveryapi.exception.SalesFaildException;
import com.example.deliveryapi.model.OrderRepository;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SalesService {
    private final UserLoginService userLoginService;
    private final OrderRepository orderRepository;
    @Transactional
    public int getSalesByDate(SalesDto salesDTO) {
        try {
            LoginDataEntity login = userLoginService.validateLoginId(salesDTO.getLoginId());

            if(salesDTO.getIsUser().equals("true") && login.getUser().isUser()){ //유저 정보를 원하고, 유저 권한이 있는지 확인
                return findSaleByDate(login.getUser().getUserId(), "user", salesDTO.getStartDate(), salesDTO.getEndDate());
            }
            else if(salesDTO.getIsStore().equals("true") && login.getUser().isStore()) {

                return findSaleByDate(login.getUser().getUserId(), "store", salesDTO.getStartDate(), salesDTO.getEndDate());
            } else {
                throw new SalesFaildException("sales find failed");
            }
        } catch (Exception e) {
            throw new SalesFaildException("sales find failed");
        }
    }

    public int findSaleByDate(long userId, String userType, LocalDate startDate, LocalDate endDate) {
        int saleTotal = 0;
        if(userType.equals("user")) {
            List<OrderDataEntity> sales = orderRepository.findByUserUserIdAndOrderDatetimeBetween(userId,startDate, endDate);
            for(OrderDataEntity sale : sales) {
                saleTotal += sale.getTotalPrice();
            }
            return saleTotal;
        } else if(userType.equals("store")) {
            List<OrderDataEntity> sales = orderRepository.findByStoreUserIdAndOrderDatetimeBetween(userId,startDate, endDate);
            for(OrderDataEntity sale : sales) {
                saleTotal += sale.getTotalPrice();
            }
            return saleTotal;
        } else {
            throw new SalesFaildException("sales find failed");
        }
    }


}

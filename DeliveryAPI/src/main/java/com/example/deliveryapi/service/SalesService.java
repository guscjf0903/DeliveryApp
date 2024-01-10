package com.example.deliveryapi.service;

import static com.example.deliveryapi.exception.ErrorCode.TOTAL_FETCH_FAILED;
import static com.example.deliveryapi.exception.ErrorCode.UNAUTHORIZED_ACCESS;

import com.example.core.dto.SalesDto;
import com.example.deliveryapi.entity.LoginDataEntity;
import com.example.deliveryapi.entity.OrderDataEntity;
import com.example.deliveryapi.enums.UserType;
import com.example.deliveryapi.exception.CustomException;
import com.example.deliveryapi.model.OrderRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SalesService {
    private final UserLoginService userLoginService;
    private final OrderRepository orderRepository;

    @Transactional(readOnly = true)
    public BigDecimal getSalesByDate(SalesDto salesDTO) {
        LoginDataEntity login = userLoginService.validateLoginId(salesDTO.getLoginId());
        if (salesDTO.getIsStore() && login.getUser().getStore()) { //유저 정보를 원하고, 유저 권한이 있는지 확인
            return findSaleByDate(login.getUser().getUserId(), UserType.STORE, salesDTO.getStartDate(),
                    salesDTO.getEndDate());
        } else if (salesDTO.getIsUser() && login.getUser().getUser()) {
            return findSaleByDate(login.getUser().getUserId(), UserType.USER, salesDTO.getStartDate(),
                    salesDTO.getEndDate());
        } else {
            throw new CustomException(UNAUTHORIZED_ACCESS);
        }
    }

    public BigDecimal findSaleByDate(long userId, UserType userType, LocalDate startDate, LocalDate endDate) {
        BigDecimal saleTotal = BigDecimal.valueOf(0);
        if (userType.equals(UserType.USER)) {
            System.out.println("test");
            List<OrderDataEntity> sales = orderRepository.findByUserUserIdAndOrderDatetimeBetween(userId, startDate,
                    endDate);
            for (OrderDataEntity sale : sales) {
                saleTotal = saleTotal.add(sale.getTotalPrice());
            }
            return saleTotal;
        } else if (userType.equals(UserType.STORE)) {
            List<OrderDataEntity> sales = orderRepository.findByStoreUserIdAndOrderDatetimeBetween(userId, startDate,
                    endDate);
            for (OrderDataEntity sale : sales) {
                saleTotal = saleTotal.add(sale.getTotalPrice());
            }
            return saleTotal;
        } else {
            throw new CustomException(TOTAL_FETCH_FAILED);
        }
    }


}

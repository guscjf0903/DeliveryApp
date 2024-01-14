package com.example.deliveryapi.service;

import static com.example.deliveryapi.exception.ErrorCode.TOTALSALETIME_FETCH_FAILED;
import static com.example.deliveryapi.exception.ErrorCode.TOTALSALE_FETCH_FAILED;
import static com.example.deliveryapi.exception.ErrorCode.UNAUTHORIZED_ACCESS;
import static com.example.deliveryapi.exception.ErrorCode.UNKNOWN_VALUE;

import com.example.core.dto.SalesDto;
import com.example.deliveryapi.entity.LoginDataEntity;
import com.example.deliveryapi.entity.OrderDataEntity;
import com.example.deliveryapi.enums.TimeType;
import com.example.deliveryapi.enums.UserType;
import com.example.deliveryapi.exception.CustomException;
import com.example.deliveryapi.model.OrderRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SalesService {
    private final UserLoginService userLoginService;
    private final OrderRepository orderRepository;

    @Transactional(readOnly = true)
    public BigDecimal getSalesByDate(long userId, String userTypeString, LocalDate startDate, LocalDate endDate) {
        LoginDataEntity login = userLoginService.validateLoginId(userId);
        UserType userType = convertToUserType(userTypeString);
        if (userType.equals(UserType.STORE) && login.getUser().getStore()) { //유저 정보를 원하고, 유저 권한이 있는지 확인
            return findSaleByDate(login.getUser().getUserId(), userType, startDate,
                    endDate);
        } else if (userType.equals(UserType.USER) && login.getUser().getUser()) {
            return findSaleByDate(login.getUser().getUserId(), userType, startDate,
                    endDate);
        } else {
            throw new CustomException(UNAUTHORIZED_ACCESS);
        }
    }

    public BigDecimal findSaleByDate(long userId, UserType userType, LocalDate startDate, LocalDate endDate) {
        BigDecimal saleTotal = BigDecimal.valueOf(0);
        if (userType.equals(UserType.USER)) {
            LocalDateTime start = startDate.atStartOfDay();;
            LocalDateTime end = endDate.atTime(LocalTime.MAX);
            List<OrderDataEntity> sales = orderRepository.findByUserUserIdAndOrderDatetimeBetween(userId, start,
                    end);
            for (OrderDataEntity sale : sales) {
                saleTotal = saleTotal.add(sale.getTotalPrice());
            }
            return saleTotal;
        } else if (userType.equals(UserType.STORE)) {
            LocalDateTime start = startDate.atStartOfDay();;
            LocalDateTime end = endDate.atTime(LocalTime.MAX);
            List<OrderDataEntity> sales = orderRepository.findByStoreUserIdAndOrderDatetimeBetween(userId, start,
                    end);
            for (OrderDataEntity sale : sales) {
                saleTotal = saleTotal.add(sale.getTotalPrice());
            }
            return saleTotal;
        } else {
            throw new CustomException(TOTALSALE_FETCH_FAILED);
        }
    }

    public BigDecimal getSalesByTime(Long loginId, LocalDate date, String apiTimeType) {
        LoginDataEntity login = userLoginService.validateLoginId(loginId);
        TimeType timeType = convertToMealType(apiTimeType);

        BigDecimal saleTotal = BigDecimal.valueOf(0);
        if(timeType.equals(TimeType.LUNCH)) {
            LocalDateTime start = LocalDateTime.of(date, LocalTime.of(10, 0, 0));
            LocalDateTime end = LocalDateTime.of(date, LocalTime.of(14, 0, 0));

            List<OrderDataEntity> sales = orderRepository.findSaleByTime(login.getUser().getUserId(), start, end);
            for (OrderDataEntity sale : sales) {
                saleTotal = saleTotal.add(sale.getTotalPrice());
            }
            System.out.println(saleTotal);
            return saleTotal;
        } else if(timeType.equals(TimeType.DINNER)) {
            LocalDateTime start = LocalDateTime.of(date, LocalTime.of(16, 0, 0));
            LocalDateTime end = LocalDateTime.of(date, LocalTime.of(20, 0, 0));

            List<OrderDataEntity> sales = orderRepository.findSaleByTime(login.getUser().getUserId(), start, end);
            for (OrderDataEntity sale : sales) {
                saleTotal = saleTotal.add(sale.getTotalPrice());
            }

            System.out.println(saleTotal);
            return saleTotal;
        } else {
            throw new CustomException(TOTALSALETIME_FETCH_FAILED);
        }
    }

    public static TimeType convertToMealType(String timeType) {
        return switch (timeType) {
            case "lunch" -> TimeType.LUNCH;
            case "dinner" -> TimeType.DINNER;
            default -> throw new CustomException(UNKNOWN_VALUE);
        };
    }

    public static UserType convertToUserType(String userType) {
        return switch (userType) {
            case "user" -> UserType.USER;
            case "store" -> UserType.STORE;
            default -> throw new CustomException(UNKNOWN_VALUE);
        };
    }


}

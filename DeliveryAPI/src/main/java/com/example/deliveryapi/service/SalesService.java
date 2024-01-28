package com.example.deliveryapi.service;

import static com.example.deliveryapi.exception.ErrorCode.TIME_FAILED;
import static com.example.deliveryapi.exception.ErrorCode.UNAUTHORIZED_ACCESS;
import static com.example.deliveryapi.exception.ErrorCode.UNKNOWN_VALUE;

import com.example.deliveryapi.entity.LoginDataEntity;
import com.example.deliveryapi.entity.OrderDataEntity;
import com.example.deliveryapi.enums.TimeType;
import com.example.deliveryapi.enums.UserType;
import com.example.deliveryapi.exception.CustomException;
import com.example.deliveryapi.model.OrderCriteriaRepository;
import com.example.deliveryapi.model.OrderDSLRepositoryImpl;
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
    private final OrderDSLRepositoryImpl orderDSLRepositoryImpl;
    private final OrderCriteriaRepository orderCriteriaRepository;


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
//            List<OrderDataEntity> sales = orderRepository.findByUserUserIdAndOrderDatetimeBetween(userId, start,
//                    end); // orderRepository를 사용 할 경우
//            List<OrderDataEntity> sales = orderDSLRepositoryImpl.findByUserOrderDatetimeBetween(userId, start,
//                    end); // orderDSLRepositoryImpl를 사용 할 경우
            List<OrderDataEntity> sales = orderCriteriaRepository.findByUserOrderDatetimeBetween(userId, start,
                    end); // orderCriteriaRepository를 사용 할 경우
            for (OrderDataEntity sale : sales) {
                saleTotal = saleTotal.add(sale.getTotalPrice());
            }
            return saleTotal;
        } else if (userType.equals(UserType.STORE)) {
            LocalDateTime start = startDate.atStartOfDay();;
            LocalDateTime end = endDate.atTime(LocalTime.MAX);
//            List<OrderDataEntity> sales = orderDSLRepositoryImpl.findByStoreOrderDatetimeBetween(userId, start,
//                    end); // orderDSLRepositoryImpl를 사용 할 경우
//            List<OrderDataEntity> sales = orderRepository.findByStoreUserIdAndOrderDatetimeBetween(userId, start,
//                    end); // orderRepository를 사용 할 경우
            List<OrderDataEntity> sales = orderCriteriaRepository.findByStoreOrderDatetimeBetween(userId, start,
                    end); // orderCriteriaRepository를 사용 할 경우
            for (OrderDataEntity sale : sales) {
                saleTotal = saleTotal.add(sale.getTotalPrice());
            }
            return saleTotal;
        } else {
            throw new CustomException(UNAUTHORIZED_ACCESS);
        }
    }

    @Transactional(readOnly = true)
    public BigDecimal getSalesByTime(Long loginId, LocalDate startDate, LocalDate endDate, String apiTimeType) {
        LoginDataEntity login = userLoginService.validateLoginId(loginId);
        TimeType timeType = convertToMealType(apiTimeType);

        BigDecimal sales = null;

        if (timeType.equals(TimeType.LUNCH)) {
            LocalDateTime startDateAndTime = LocalDateTime.of(startDate, LocalTime.of(10, 0, 0));
            LocalDateTime endDateAndTime = LocalDateTime.of(endDate, LocalTime.of(14, 0, 0));

            //sales = orderRepository.findSaleByTime(login.getUser().getUserId(), startDateAndTime, endDateAndTime);// orderRepository를 사용 할 경우
            sales = orderDSLRepositoryImpl.findSaleByTime(login.getUser().getUserId(), startDateAndTime, endDateAndTime);// orderDSLRepositoryImpl를 사용 할 경우
            //sales = orderCriteriaRepository.findSaleByTime(login.getUser().getUserId(), startDateAndTime, endDateAndTime);// orderCriteriaRepository를 사용 할 경우
        } else if (timeType.equals(TimeType.DINNER)) {
            LocalDateTime startDateAndTime = LocalDateTime.of(startDate, LocalTime.of(16, 0, 0));
            LocalDateTime endDateAndTime = LocalDateTime.of(endDate, LocalTime.of(20, 0, 0));

            //sales = orderRepository.findSaleByTime(login.getUser().getUserId(), startDateAndTime, endDateAndTime);// orderRepository를 사용 할 경우
            sales = orderDSLRepositoryImpl.findSaleByTime(login.getUser().getUserId(), startDateAndTime, endDateAndTime);// orderDSLRepositoryImpl를 사용 할 경우
            //sales = orderCriteriaRepository.findSaleByTime(login.getUser().getUserId(), startDateAndTime, endDateAndTime);// orderCriteriaRepository를 사용 할 경우
        } else {
            throw new CustomException(TIME_FAILED);
        }

        return sales == null ? BigDecimal.ZERO : sales;
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

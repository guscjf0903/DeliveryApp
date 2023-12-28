package com.example.deliveryapi.service;

import com.example.core.dto.SalesDto;
import com.example.core.dto.SalesResponse;
import com.example.deliveryapi.entity.LoginDataEntity;
import com.example.deliveryapi.entity.OrderDataEntity;
import com.example.deliveryapi.entity.SalesStoreEntity;
import com.example.deliveryapi.entity.SalesUserEntity;
import com.example.deliveryapi.exception.SalesFailedException;
import com.example.deliveryapi.model.OrderRepository;
import com.example.deliveryapi.model.SalesStoreRepository;
import com.example.deliveryapi.model.SalesUserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SalesService {
    private final SalesStoreRepository salesStoreRepository;
    private final SalesUserRepository salesUserRepository;
    private final UserLoginService userLoginService;
    private final UserSignupService userSignupService;
    private final OrderRepository orderRepository;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Transactional
    public Optional<SalesResponse> getTotalSales(SalesDto salesDto) {
        try {
            LoginDataEntity loginDataEntity = checkLoginData(salesDto);

            if(salesDto.isCheckStore()) {
                userSignupService.checkStorePermission(loginDataEntity);
                return Optional.of(getStoreSales(salesDto));
            } else if(salesDto.isCheckUser()) {
                userSignupService.checkUserPermission(loginDataEntity);
                return Optional.of(getUserSales(salesDto));
            } else {
                return Optional.empty();
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Optional.empty();
        }
    }

    private LoginDataEntity checkLoginData(SalesDto salesDto) {
        return userLoginService.validateLoginId(salesDto.getLoginId());
    }

    public SalesResponse getUserSales(SalesDto salesDto) {
        try {
            LoginDataEntity loginInfo = userLoginService.validateLoginId(salesDto.getLoginId());
            SalesUserEntity saleUserData = salesUserRepository.findByUserUserIdAndMonth(loginInfo.getUser().getUserId(), salesDto.getSalesMonth());

            return new SalesResponse(saleUserData.getUser().getUserName(), saleUserData.getMonth(), saleUserData.getMonthSales());
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new SalesFailedException(e.getMessage());
        }
    }

    public SalesResponse getStoreSales(SalesDto salesDto) {
        try {
            LoginDataEntity loginInfo = userLoginService.validateLoginId(salesDto.getLoginId());
            SalesStoreEntity saleStoreData = salesStoreRepository.findByStoreUserIdAndMonth(loginInfo.getUser().getUserId(), salesDto.getSalesMonth());

            return new SalesResponse(saleStoreData.getStore().getCompanyName(), saleStoreData.getMonth(), saleStoreData.getMonthSales());
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new SalesFailedException(e.getMessage());
        }
    }

    @Transactional
    public void setSales(OrderDataEntity orderDataEntity) {
        setStoreSales(orderDataEntity);
        setUserSales(orderDataEntity);
    }

    private void setUserSales(OrderDataEntity orderDataEntity) {
        try {
            SalesUserEntity salesUserEntity = salesUserRepository.findByUserUserIdAndMonth(orderDataEntity.getUser().getUserId(), orderDataEntity.getOrderDate().getMonthValue());
            if(salesUserEntity != null) { // 이미 해당 user의 sales가 존재하면 update
                salesUserEntity.setMonthSales(salesUserEntity.getMonthSales() + orderDataEntity.getTotalPrice());

            } else { // 해당 store의 sales가 존재하지 않으면 insert
                salesUserEntity = new SalesUserEntity();
                salesUserEntity.setUser(orderDataEntity.getUser());
                int month = orderRepository.findMonthByOrderId(orderDataEntity.getOrderId());
                System.out.println(month);
                salesUserEntity.setMonth(month);
                salesUserEntity.setMonthSales(orderDataEntity.getTotalPrice());
            }
            salesUserRepository.save(salesUserEntity);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new SalesFailedException(e.getMessage());
        }
    }

    private void setStoreSales(OrderDataEntity orderDataEntity) {
        try {
            SalesStoreEntity salesStoreEntity = salesStoreRepository.findByStoreUserIdAndMonth(orderDataEntity.getStore().getUserId(), orderDataEntity.getOrderDate().getMonthValue());
            if(salesStoreEntity != null) { // 이미 해당 store의 sales가 존재하면 update
                salesStoreEntity.setMonthSales(salesStoreEntity.getMonthSales() + orderDataEntity.getTotalPrice());
            } else { // 해당 store의 sales가 존재하지 않으면 insert
                salesStoreEntity = new SalesStoreEntity();
                salesStoreEntity.setStore(orderDataEntity.getStore());
                int month = orderRepository.findMonthByOrderId(orderDataEntity.getOrderId());
                salesStoreEntity.setMonth(month);
                salesStoreEntity.setMonthSales(orderDataEntity.getTotalPrice());
            }

            salesStoreRepository.save(salesStoreEntity);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new SalesFailedException(e.getMessage());
        }
    }
}

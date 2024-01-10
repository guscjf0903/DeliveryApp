package com.example.deliveryapi.service;


import static com.example.deliveryapi.exception.ErrorCode.NOT_FOUND_USERORSTORE_TOKEN;
import static com.example.deliveryapi.exception.ErrorCode.USERSIGNUP_FAILED;

import com.example.core.dto.*;
import com.example.deliveryapi.entity.LoginDataEntity;
import com.example.deliveryapi.entity.UserSignupDataEntity;
import com.example.deliveryapi.exception.CustomException;
import com.example.deliveryapi.model.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserSignupService {
    private final UserRepository userRepository;

    @Transactional
    public void registerUser(SignupDto signupDTO) {
        try{
            UserSignupDataEntity user = UserSignupDataEntity.of(signupDTO);
            userRepository.save(user);
        } catch (Exception e) {
            throw new CustomException(USERSIGNUP_FAILED);
        }
    }
    public void checkStorePermission(LoginDataEntity login) {
        if (!login.getUser().getStore()) {
            throw new CustomException(NOT_FOUND_USERORSTORE_TOKEN);
        }
    }

    public void checkUserPermission(LoginDataEntity login) {
        if (!login.getUser().getUser()) {
            throw new CustomException(NOT_FOUND_USERORSTORE_TOKEN);
        }
    }

}

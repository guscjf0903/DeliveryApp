package com.example.deliveryapi.service;

import static com.example.deliveryapi.entity.UserSignupDataEntity.toEntity;

import com.example.core.dto.*;
import com.example.deliveryapi.entity.LoginDataEntity;
import com.example.deliveryapi.entity.UserSignupDataEntity;
import com.example.deliveryapi.exception.InvalidTokenException;
import com.example.deliveryapi.exception.UserRegistrationException;
import com.example.deliveryapi.model.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserSignupService {
    private final UserRepository userRepository;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Transactional
    public void registerUser(SignupDto signupDTO) {
        try{
            UserSignupDataEntity user = toEntity(signupDTO);
            userRepository.save(user);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new UserRegistrationException("user registration failed", e);
        }
    }
    public void checkStorePermission(LoginDataEntity login) {
        if (!login.getUser().isStore()) {
            throw new InvalidTokenException("no permission");
        }
    }

    public void checkUserPermission(LoginDataEntity login) {
        if (!login.getUser().isUser()) {
            throw new InvalidTokenException("no permission");
        }
    }

}

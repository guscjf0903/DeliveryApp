package com.example.deliveryapi.service;

import static com.example.deliveryapi.entity.UserSignupData.toEntity;

import com.example.core.dto.*;
import com.example.deliveryapi.entity.UserSignupData;
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
        UserSignupData user = toEntity(signupDTO);
        userRepository.save(user);
    }

}

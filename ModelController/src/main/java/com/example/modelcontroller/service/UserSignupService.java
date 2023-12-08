package com.example.modelcontroller.service;

import static com.example.modelcontroller.entity.User.toEntity;

import com.example.core.dto.*;
import com.example.modelcontroller.entity.User;
import com.example.modelcontroller.model.UserRepository;
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
        User user = toEntity(signupDTO);
        userRepository.save(user);
    }

}

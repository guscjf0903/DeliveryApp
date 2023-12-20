package com.example.deliveryapi.service;

import com.example.core.dto.LoginDto;
import com.example.core.dto.LoginResponse;
import com.example.deliveryapi.entity.LoginDataEntity;
import com.example.deliveryapi.entity.UserSignupDataEntity;
import com.example.deliveryapi.exception.InvalidTokenException;
import com.example.deliveryapi.exception.PasswordMismatchException;
import com.example.deliveryapi.model.LoginRepository;
import com.example.deliveryapi.model.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserLoginService {
    private final UserRepository userRepository;
    private final LoginRepository loginRepository;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Transactional
    public Optional<LoginResponse> loginSuccessGenerateToken(LoginDto loginDTO) {
        try {
            UserSignupDataEntity user = validatePasswordAndGetUserData(loginDTO.getUserName(), loginDTO.getPassword());

            LoginDataEntity login = LoginDataEntity.of(user);
            loginRepository.save(login);
            LoginResponse loginResponse = new LoginResponse(login.getLoginId());

            return Optional.of(loginResponse);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Optional.empty();
        }
    }

    private UserSignupDataEntity validatePasswordAndGetUserData(String userName, String password) {
        UserSignupDataEntity userSignupData = userRepository.findByUserName(userName);
        if (!userSignupData.getPassword().equals(password)) {
            throw new PasswordMismatchException("miss match password");
        }

        return userSignupData;
    }

    public LoginDataEntity validateLoginId(int loginId) {
        return loginRepository.findById(loginId)
                .orElseThrow(() -> new InvalidTokenException("invalid login id"));
    }

}

package com.example.deliveryapi.service;

import static com.example.deliveryapi.exception.ErrorCode.NOTFOUND_USER;
import static com.example.deliveryapi.exception.ErrorCode.NOT_FOUND_LOGINID;
import static com.example.deliveryapi.exception.ErrorCode.PASSWORD_DISMATCH;

import com.example.core.dto.LoginDto;
import com.example.core.dto.LoginResponse;
import com.example.deliveryapi.entity.LoginDataEntity;
import com.example.deliveryapi.entity.UserSignupDataEntity;
import com.example.deliveryapi.exception.CustomException;
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
        UserSignupDataEntity user = validatePasswordAndGetUserData(loginDTO.getUserName(), loginDTO.getPassword());

        LoginDataEntity login = LoginDataEntity.of(user);
        if(loginRepository.existsByUserUserId(login.getUser().getUserId())) { // userId가 존재하면 삭제
            loginRepository.deleteByUserUserId(login.getUser().getUserId());
        }
        loginRepository.save(login);
        LoginResponse loginResponse = new LoginResponse(login.getLoginId());

        return Optional.of(loginResponse);
    }

    private UserSignupDataEntity validatePasswordAndGetUserData(String userName, String password) {
        UserSignupDataEntity userSignupData = userRepository.findByUserName(userName);
        if(userSignupData == null) {
            throw new CustomException(NOTFOUND_USER);
        }
        if (!userSignupData.getPassword().equals(password)) {
            throw new CustomException(PASSWORD_DISMATCH);
        }

        return userSignupData;
    }

    @Transactional(readOnly = true)
    public LoginDataEntity validateLoginId(Long loginId) {
        return loginRepository.findById(loginId)
                .orElseThrow(() -> new CustomException(NOT_FOUND_LOGINID));
    }

}

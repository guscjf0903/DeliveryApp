package com.example.deliveryapi.service;

import com.example.core.dto.LoginDto;
import com.example.core.dto.LoginResponse;
import com.example.deliveryapi.entity.LoginData;
import com.example.deliveryapi.entity.UserSignupData;
import com.example.deliveryapi.model.LoginRepository;
import com.example.deliveryapi.model.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserLoginService {
    private final UserRepository userRepository;
    private final LoginRepository loginRepository;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Transactional
    public ResponseEntity<LoginResponse> loginUser(LoginDto loginDTO) {
        try {
            UserSignupData user = userRepository.findByUserName(loginDTO.getUserName());
            if (!user.getPassword().equals(loginDTO.getPassword())) {
                throw new Exception();
            }
            LoginData login = LoginData.toEntity(user);
            loginRepository.save(login);
            LoginResponse loginResponse = new LoginResponse(login.getLoginId());

            return ResponseEntity.ok(loginResponse);
        } catch (Exception e) {
            LoginResponse loginResponse = new LoginResponse(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(loginResponse);
        }
    }

}

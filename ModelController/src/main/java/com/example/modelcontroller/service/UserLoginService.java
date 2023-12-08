package com.example.modelcontroller.service;

import com.example.core.dto.LoginDto;
import com.example.core.dto.LoginResponse;
import com.example.modelcontroller.entity.User;
import com.example.modelcontroller.model.UserRepository;
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
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Transactional
    public ResponseEntity<LoginResponse> loginUser(LoginDto loginDTO) {
        try{
            User user = userRepository.findByUserName(loginDTO.getUserName());
            if(!user.getPassword().equals(loginDTO.getPassword())) {
                throw new Exception();
            }
            LoginResponse loginResponse = new LoginResponse(user.getUserId());

            return ResponseEntity.ok(loginResponse);

        } catch (Exception e) {
            LoginResponse loginResponse = new LoginResponse(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(loginResponse);
        }
    }

}

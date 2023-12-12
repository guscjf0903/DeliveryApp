package com.example.core.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class LoginResponse {
    private final Long loginId;

    @JsonCreator
    public LoginResponse(@JsonProperty("loginId") Long loginId) {
        this.loginId = loginId;
    }
}


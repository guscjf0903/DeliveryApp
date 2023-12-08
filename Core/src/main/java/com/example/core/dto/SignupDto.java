package com.example.core.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SignupDto {
    @NotBlank
    private String userName;
    private String password;
    @Email(message = "Please enter a valid email address")
    private String email;
    private String companyName;
    private String address;
    private boolean store;
    private boolean user;
}

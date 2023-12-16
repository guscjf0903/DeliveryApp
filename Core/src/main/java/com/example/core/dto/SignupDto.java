package com.example.core.dto;

import com.example.core.valiator.Password;
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
    @NotBlank(message = "Please enter a username")
    private String userName;

    @Password
    @NotBlank(message = "Please enter a password")
    private String password;

    @NotBlank(message = "Please enter a name")
    @Email(message = "Please enter a valid email address")
    private String email;
    private String companyName;
    private String address;
    private boolean store;
    private boolean user;
}

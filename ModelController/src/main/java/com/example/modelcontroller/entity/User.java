package com.example.modelcontroller.entity;

import com.example.core.dto.SignupDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users", schema = "delivery_schema")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "username", unique = true)
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "address")
    private String address;

    @Column(name = "store_role")
    private boolean store;

    @Column(name = "user_role")
    private boolean user;

    public static User toEntity(SignupDto signupDto) {
        User user = new User();
        user.setUserName(signupDto.getUserName());
        user.setPassword(signupDto.getPassword());
        user.setEmail(signupDto.getEmail());
        user.setCompanyName(signupDto.getCompanyName());
        user.setAddress(signupDto.getAddress());
        user.setStore(signupDto.isStore());
        user.setUser(signupDto.isUser());

        return user;
    }


}

package com.example.deliveryapi.entity;

import com.example.core.dto.SignupDto;
import jakarta.persistence.*;
import java.util.List;
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
public class UserSignupData {
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

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<MenuAddData> menus;


    public static UserSignupData toEntity(SignupDto signupDto) {
        UserSignupData user = new UserSignupData();
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

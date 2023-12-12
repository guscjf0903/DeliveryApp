package com.example.deliveryapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "login_info", schema = "delivery_schema")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LoginData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "login_id")
    private Long loginId;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserSignupData user;

    public static LoginData toEntity(UserSignupData user) {
        LoginData login = new LoginData();
        login.setUser(user);
        return login;
    }

}

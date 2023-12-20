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
public class LoginDataEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "login_id")
    private Long loginId;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserSignupDataEntity user;

    public LoginDataEntity(UserSignupDataEntity user) {
        this.user = user;
    }

    public static LoginDataEntity of(UserSignupDataEntity user) {
        return new LoginDataEntity(user);
    }

}

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
public class UserSignupDataEntity {
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
    private List<MenuAddDataEntity> menus;

    public UserSignupDataEntity(String userName, String password, String email, String companyName, String address, boolean store, boolean user) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.companyName = companyName;
        this.address = address;
        this.store = store;
        this.user = user;
    }

    public static UserSignupDataEntity of(SignupDto signupDto) {

        return new UserSignupDataEntity(signupDto.getUserName(), signupDto.getPassword(), signupDto.getEmail(),
                signupDto.getCompanyName(), signupDto.getAddress(), signupDto.getStore(), signupDto.getUser());
    }


}

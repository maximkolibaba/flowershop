package com.accenture.flowershop.be.entity.user;

import com.sun.deploy.security.MSCryptoDSASignature;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Enumerated(EnumType.STRING)
    @Getter
    @Setter
    private UserType userType;

    @Getter
    @Setter
    private String firstName;

    @Getter
    @Setter
    private String lastName;

    @Getter
    @Setter
    private String login;

    @Getter
    @Setter
    private String password;

    @Getter
    @Setter
    private String address;

    @Getter
    @Setter
//    @Value("#{config['user.defaultBalance']}")
//    @Value("${user.defaultBalance}")
    private BigDecimal balance;

    @Getter
    @Setter
//    @Value("#{config['user.defaultDiscount']}")
//    @Value("${user.defaultDiscount}")
    private Integer discount;

    public User() {
    }

    public User(String login, String password, String firstName, String lastName, String address) {
        userType = UserType.CUSTOMER;
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
        this.address = address;
        //
        this.balance = new BigDecimal(500);
        this.discount = 0;
    }
}


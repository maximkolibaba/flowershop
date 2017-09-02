package com.accenture.flowershop.be.entity.user;

import lombok.Getter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    private String firstName;
    private String lastName;
    @Getter
    private String login;
    @Getter
    private String password;
    private String address;
    private BigDecimal balance;
    private int discount;

    public User() {
    }
}


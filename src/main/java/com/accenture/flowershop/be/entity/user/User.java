package com.accenture.flowershop.be.entity.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String firstName;
    private String lastName;
    private String login;
    private String password;
    private UserType userType;
    private String address;
    private BigDecimal balance;
    private int discount;

    public User() {
    }
}


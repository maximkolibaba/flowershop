package com.accenture.flowershop.be.entity.user;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    //@Enumerated(EnumType.STRING)
    //@Getter
    //@Setter
    //private UserType userType;
    @Getter
    @Setter
    private Boolean isAdmin;

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
    private BigDecimal balance;

    @Getter
    @Setter
    private Integer discount;

    public User() {
    }

    public User(String login, String password, String firstName, String lastName, String address, BigDecimal balance, Integer discount) {
        //userType = UserType.CUSTOMER;
        isAdmin = false;
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
        this.address = address;
        this.balance = balance;
        this.discount = discount;
    }
}


package com.accenture.flowershop.be.entity.user;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.math.BigDecimal;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean isAdmin;
    private String firstName;
    private String lastName;
    private String login;
    private String password;
    private String address;
    private BigDecimal balance;
    private Integer discount;

    public User() {
    }

    public User(String login, String password, String firstName, String lastName,
                String address, BigDecimal balance, Integer discount) {
        isAdmin = false;
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
        this.address = address;
        this.balance = balance;
        this.discount = discount;
    }

    public void subtractBalance(BigDecimal amount) {
        balance = balance.subtract(amount);
    }
}


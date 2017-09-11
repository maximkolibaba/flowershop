package com.accenture.flowershop.be.business.user;

import com.accenture.flowershop.be.entity.user.User;

import java.math.BigDecimal;
import java.util.List;

public interface UserBusinessService {
    User login(String login, String password);

    List<UserRegisterResult> register(String login, String password, String firstName, String lastName, String address);

    User payOrder(User user, BigDecimal price);
}

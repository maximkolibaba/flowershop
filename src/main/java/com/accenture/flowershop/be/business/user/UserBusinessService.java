package com.accenture.flowershop.be.business.user;

import com.accenture.flowershop.be.entity.user.User;

public interface UserBusinessService {
    User login(String login, String password);

    User register(String login, String password, String address);
}

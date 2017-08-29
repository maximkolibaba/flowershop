package com.accenture.flowershop.be.business.user;

import com.accenture.flowershop.be.entity.user.User;

public interface UserBusinessService {
    User login(String user, String password);

    User register(String user, String password, String address);
}

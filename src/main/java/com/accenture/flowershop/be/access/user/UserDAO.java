package com.accenture.flowershop.be.access.user;

import com.accenture.flowershop.be.entity.user.User;

public interface UserDAO  {
    User getByLogin(String login);
    boolean create(User user);
    User update(User user);
}
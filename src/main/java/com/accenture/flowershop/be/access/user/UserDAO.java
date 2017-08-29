package com.accenture.flowershop.be.access.user;

import com.accenture.flowershop.be.access.DAO;
import com.accenture.flowershop.be.entity.user.User;

import java.util.List;

public interface UserDAO extends DAO<User> {
    List<User> getByName(String firstName, String lastName);
    User getByLogin(String login);
    User getByEmail(String email);
    User getByPhoneNumber(String phoneNumber);
}

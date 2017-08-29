package com.accenture.flowershop.be.access;

import com.accenture.flowershop.be.entity.user.User;

import java.util.List;

public interface UserDAO extends DAO<User> {
    List<User> findByName(String firstName, String lastName);
    User findByEmail(String email);
    User findByPhoneNumber(String phoneNumber);
}

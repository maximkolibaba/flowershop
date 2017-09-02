package com.accenture.flowershop.be.business.user;

import com.accenture.flowershop.be.access.user.UserDAO;
import com.accenture.flowershop.be.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserBusinessServiceImpl implements UserBusinessService {
    @Autowired
    private UserDAO dao;

    public User login(String login, String password) {
        if (login == null || password == null) {
            return null;
        }

        User user = dao.getByLogin(login);

        if (user != null) {
            if (user.getPassword().equals(password)) { // TODO ИСПОЛЬЗОВАТЬ ТОЛЬКО EQUALS!!!!!!!!
                return user;
            }
        }

        return null;
    }

    public User register(String login, String password, String address) {
        return null;
    }
}

package com.accenture.flowershop.be.business.user;

import com.accenture.flowershop.be.access.user.UserDAO;
import com.accenture.flowershop.be.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserBusinessServiceImpl implements UserBusinessService {
    @Autowired
    private UserDAO dao;

    public User login(String login, String password) {
        if (login.length() == 0 || password.length() == 0) {
            return null;
        }

        User user = dao.getByLogin(login);

        if (user != null) {
            if (user.getPassword().equals(password)) {
                return user;
            }
        }

        return null;
    }

    public List<UserRegisterResult> register(String login, String password, String firstName, String lastName, String address) {
        List<UserRegisterResult> results = new ArrayList<UserRegisterResult>();

        if (password.length() == 0) {
            results.add(UserRegisterResult.NO_PASSWORD);
        }

        if (login.length() == 0) {
            results.add(UserRegisterResult.NO_LOGIN);
        } else if (dao.getByLogin(login) != null) {
            results.add(UserRegisterResult.LOGIN_IS_USED);
        }

        if (firstName.length() == 0 || lastName.length() == 0 || address.length() == 0) {
            results.add(UserRegisterResult.INCOMPLETE_USER_INFO);
        }

        if (results.size() == 0) {
            User user = new User(login, password, firstName, lastName, address);
            if (dao.create(user) != null) {
                results.add(UserRegisterResult.SUCCESS);
            }
        }

        return results;
    }
}

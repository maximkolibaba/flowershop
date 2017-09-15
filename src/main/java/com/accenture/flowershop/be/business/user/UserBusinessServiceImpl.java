package com.accenture.flowershop.be.business.user;

import com.accenture.flowershop.be.access.user.UserDAO;
import com.accenture.flowershop.be.entity.user.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Service
public class UserBusinessServiceImpl implements UserBusinessService {
    @Autowired
    private UserDAO dao;

    @Autowired
    private Properties properties;

    public User login(String login, String password) {
        if (login == null || login.length() == 0 || password == null || password.length() == 0) {
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

        if (results.isEmpty()) {
            BigDecimal balance = new BigDecimal(properties.getProperty("user.defaultBalance"));
            Integer discount = Integer.parseInt(properties.getProperty("user.defaultDiscount"));
            User user = new User(login, password, firstName, lastName, address, balance, discount);
            if (dao.create(user) != null) {
                results.add(UserRegisterResult.SUCCESS);
            }
        }

        return results;
    }

    public User payOrder(User user, BigDecimal price) {
        user.setBalance(user.getBalance().subtract(price)); // todo in user
        return dao.update(user);
    }
}

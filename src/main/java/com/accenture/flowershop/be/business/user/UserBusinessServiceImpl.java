package com.accenture.flowershop.be.business.user;

import com.accenture.flowershop.be.access.user.UserRepository;
import com.accenture.flowershop.be.entity.user.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Properties;

@Service
public class UserBusinessServiceImpl implements UserBusinessService {
    @Autowired
    private UserRepository repository;

    @Autowired
    private Properties properties;

    public User login(String login, String password) {
        if (StringUtils.isEmpty(login) || StringUtils.isEmpty(password)) {
            return null;
        }

//        User user = dao.getByLogin(login);
        User user = repository.findByLogin(login);


        if (user != null) {
            if (user.getPassword().equals(password)) {
                return user;
            }
        }

        return null;
    }

    public User register(String login, String password, String firstName, String lastName, String address) {
        BigDecimal balance = new BigDecimal(properties.getProperty("user.defaultBalance"));
        Integer discount = Integer.parseInt(properties.getProperty("user.defaultDiscount"));
        User user = new User(login, password, firstName, lastName, address, balance, discount);
//        return dao.create(user);
        return repository.save(user);
    }

    public User payOrder(User user, BigDecimal price) {
        user.subtractBalance(price);
//        return dao.update(user);
        return repository.save(user);
    }
}

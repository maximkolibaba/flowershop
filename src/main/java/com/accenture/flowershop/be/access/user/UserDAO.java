package com.accenture.flowershop.be.access.user;

import com.accenture.flowershop.be.entity.user.User;
import org.springframework.transaction.annotation.Transactional;

@Deprecated
public interface UserDAO {
    User getByLogin(String login);

    @Transactional
    User create(User user);

    @Transactional
    User update(User user);
}
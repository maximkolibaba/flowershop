package com.accenture.flowershop.be.access.user;

import com.accenture.flowershop.be.entity.user.User;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Component
public class UserDAOImpl implements UserDAO {
    @PersistenceContext
    private EntityManager entityManager;

    public User getByLogin(String login) {
        try {
            TypedQuery<User> query = entityManager.createQuery("select u from User u where u.login=:login", User.class);
            query.setParameter("login", login);
            return query.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    public boolean create(User user) {
        return false;
    }

    public User update(User user) {
        return null;
    }
}

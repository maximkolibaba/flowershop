package com.accenture.flowershop.be.access.order;

import com.accenture.flowershop.be.entity.order.Order;
import com.accenture.flowershop.be.entity.user.User;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import org.slf4j.Logger;

@Deprecated
@Component
public class OrderDAOImpl implements OrderDAO {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderDAO.class);

    @PersistenceContext
    private EntityManager entityManager;

    public List<Order> getAll() {
        try {
            TypedQuery<Order> query = entityManager.createQuery("select o from Order o", Order.class);
            return query.getResultList();
        } catch (NoResultException ex) {
            return null;
        }
    }

    public List<Order> getByUser(User user) {
        try {
            TypedQuery<Order> query = entityManager.createQuery(
                    "select o from Order o where o.user=:user", Order.class);
            query.setParameter("user", user);
            return query.getResultList();
        } catch (NoResultException ex) {
            return null;
        }
    }

    public Order getById(Long id) {
        try {
            TypedQuery<Order> query = entityManager.createQuery(
                    "select o from Order o where o.id=:id", Order.class);
            query.setParameter("id", id);
            return query.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    public Order create(Order order) {
        try {
            entityManager.persist(order);
            return order;
        } catch (NoResultException ex) {
            LOGGER.error("can't create order", ex);
            return null;
        }
    }

    public Order update(Order order) {
        try {
            entityManager.merge(order);
            return order;
        } catch (NoResultException ex) {
            return null;
        }
    }

    public boolean delete(Order order) {
        try {
            entityManager.remove(entityManager.find(Order.class, order.getId()));
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}

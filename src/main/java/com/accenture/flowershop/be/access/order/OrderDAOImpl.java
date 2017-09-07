package com.accenture.flowershop.be.access.order;

import com.accenture.flowershop.be.entity.order.Order;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class OrderDAOImpl implements OrderDAO {
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

    public Order getById(Long id) {
        try {
            TypedQuery<Order> query = entityManager.createQuery("select o from Order o where o.id=:id", Order.class);
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
        // TODO: implement
        return false;
    }
}

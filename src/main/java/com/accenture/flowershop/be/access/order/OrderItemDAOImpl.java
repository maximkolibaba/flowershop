package com.accenture.flowershop.be.access.order;

import com.accenture.flowershop.be.entity.order.Order;
import com.accenture.flowershop.be.entity.order.OrderItem;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class OrderItemDAOImpl implements OrderItemDAO {
    @PersistenceContext
    private EntityManager entityManager;

    public OrderItem create(OrderItem item) {
        try {
            entityManager.persist(item);
            return item;
        } catch (NoResultException ex) {
            return null;
        }
    }

    public boolean delete(OrderItem item) {
        try {
            entityManager.remove(entityManager.find(OrderItem.class, item.getId()));
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public List<OrderItem> getByOrder(Order order) {
        try {
            TypedQuery<OrderItem> query = entityManager.createQuery(
                    "select o from OrderItem o where o.order=:order", OrderItem.class);
            query.setParameter("order", order);
            List<OrderItem> items = query.getResultList();
            return items;
        } catch (NoResultException ex) {
            return null;
        }
    }
}

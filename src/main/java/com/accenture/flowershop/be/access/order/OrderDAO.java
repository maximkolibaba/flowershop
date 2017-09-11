package com.accenture.flowershop.be.access.order;

import com.accenture.flowershop.be.entity.order.Order;
import com.accenture.flowershop.be.entity.user.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OrderDAO {
    List<Order> getAll();

    List<Order> getByUser(User user);

    Order getById(Long id);

    @Transactional
    Order create(Order order);

    @Transactional
    Order update(Order order);

    @Transactional
    boolean delete(Order order);
}

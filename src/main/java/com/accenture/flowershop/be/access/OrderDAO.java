package com.accenture.flowershop.be.access;

import com.accenture.flowershop.be.entity.order.Order;
import com.accenture.flowershop.be.entity.user.User;

import java.util.List;

public interface OrderDAO extends DAO<Order> {
    List<Order> findByPrice(double price);
    List<Order> findByUser(User user);
}

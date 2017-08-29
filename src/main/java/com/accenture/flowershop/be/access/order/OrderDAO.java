package com.accenture.flowershop.be.access.order;

import com.accenture.flowershop.be.access.DAO;
import com.accenture.flowershop.be.entity.order.Order;
import com.accenture.flowershop.be.entity.user.User;

import java.util.List;

public interface OrderDAO extends DAO<Order> {
    List<Order> getByPrice(double price);
    List<Order> getByUser(User user);
}

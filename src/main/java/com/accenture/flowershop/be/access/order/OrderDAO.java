package com.accenture.flowershop.be.access.order;

import com.accenture.flowershop.be.entity.order.Order;

import java.util.List;

public interface OrderDAO {
    List<Order> getAll();
    Order getById(Long id);
    boolean create(Order order);
    Order update(Order order);
    boolean delete(Order order);
}

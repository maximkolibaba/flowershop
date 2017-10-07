package com.accenture.flowershop.be.business.order;

import com.accenture.flowershop.be.entity.order.Order;
import com.accenture.flowershop.be.entity.user.User;
import com.accenture.flowershop.fe.Cart;

import java.util.List;

public interface OrderBusinessService {
    List<Order> getAllOrders();

    Order createOrder(Cart cart, User user);

    boolean cancelOrder(Order order);

    Order updateStatus(Order order);

    List<Order> getUserOrders(User user);

    Order findById(Long id);
}

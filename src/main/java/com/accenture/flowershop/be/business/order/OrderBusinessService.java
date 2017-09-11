package com.accenture.flowershop.be.business.order;

//    todo make order repository not dao or mb later

import com.accenture.flowershop.be.entity.order.Order;
import com.accenture.flowershop.be.entity.order.OrderStatus;
import com.accenture.flowershop.be.entity.user.User;
import com.accenture.flowershop.fe.Cart;

import java.util.List;

public interface OrderBusinessService {
    List<Order> getAllOrders();

    Order createOrder(Cart cart, User user);

    boolean cancelOrder(Order order);

    boolean cancelOrder(Long id);

    Order setStatus(Order order, OrderStatus status);

    List<Order> getUserOrders(User user);
}

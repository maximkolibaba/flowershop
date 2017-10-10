package com.accenture.flowershop.be.business;

import com.accenture.flowershop.be.entity.flower.Flower;
import com.accenture.flowershop.be.entity.order.Order;
import com.accenture.flowershop.be.entity.user.User;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface MainBusinessService {
    Flower orderFlower(Flower flower, int amount);

    void cancelOrder(Order order);

    void cancelOrder(Long orderId);

    Order updateOrderStatus(Order order);

    Order updateOrderStatus(Long orderId);

    Collection<Flower> getFlowers(Collection<Long> flowersIds);

    List<Order> getUserOrders(User user);

    List<Order> getAllOrders();

    User registerUser(String login, String password, String firstName, String lastName, String address);

    User registerUser(Map<String, String[]> parameters);
}

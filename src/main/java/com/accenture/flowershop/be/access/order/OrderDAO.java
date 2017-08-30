package com.accenture.flowershop.be.access.order;

//import com.accenture.flowershop.be.access.DAO;
import com.accenture.flowershop.be.entity.order.Order;
import com.accenture.flowershop.be.entity.user.User;

import java.util.List;
//public interface OrderDAO extends DAO<Order> {
public interface OrderDAO {
    List<Order> getAll();
    Order getById(Long id);
    boolean create(Order order);
    Order update(Order order);
    boolean delete(Order order);
    //
//    List<Order> getByPrice(double price);
//    List<Order> getByUser(User user);
}

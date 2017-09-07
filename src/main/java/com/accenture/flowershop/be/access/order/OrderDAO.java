package com.accenture.flowershop.be.access.order;

import com.accenture.flowershop.be.entity.order.Order;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// TODO: будет Repository, нужны ли там @Transactional - хз
public interface OrderDAO {
    List<Order> getAll();

    Order getById(Long id);

    @Transactional
    Order create(Order order);

    @Transactional
    Order update(Order order);

    @Transactional
    boolean delete(Order order);
}

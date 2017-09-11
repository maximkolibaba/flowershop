package com.accenture.flowershop.be.access.order;

import com.accenture.flowershop.be.entity.order.Order;
import com.accenture.flowershop.be.entity.order.OrderItem;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OrderItemDAO {
    @Transactional
    OrderItem create(OrderItem item);

    @Transactional
    boolean delete(OrderItem item);

    List<OrderItem> getByOrder(Order order);
}

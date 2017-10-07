package com.accenture.flowershop.be.business;

import com.accenture.flowershop.be.entity.flower.Flower;
import com.accenture.flowershop.be.entity.order.Order;

import java.util.Collection;

public interface MainBusinessService {
    void cancelOrder(Order order);

    Order updateOrderStatus(Order order);

    Collection<Flower> getFlowers(Collection<Long> flowersIds);
}

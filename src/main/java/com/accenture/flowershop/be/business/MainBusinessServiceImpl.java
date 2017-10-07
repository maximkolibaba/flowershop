package com.accenture.flowershop.be.business;

import com.accenture.flowershop.be.business.flower.FlowerBusinessService;
import com.accenture.flowershop.be.business.order.OrderBusinessService;
import com.accenture.flowershop.be.business.user.UserBusinessService;
import com.accenture.flowershop.be.entity.flower.Flower;
import com.accenture.flowershop.be.entity.order.Order;
import com.accenture.flowershop.be.entity.order.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class MainBusinessServiceImpl implements MainBusinessService {
    @Autowired
    private FlowerBusinessService flowerBusinessService;

    @Autowired
    private OrderBusinessService orderBusinessService;

    @Autowired
    private UserBusinessService userBusinessService;

    public void cancelOrder(Order order) {

    }

    public Order updateOrderStatus(Order order) {
        order = orderBusinessService.updateStatus(order);
        if (order != null && order.getOrderStatus() == OrderStatus.PROCESSING) {
            // TODO: move downward
            userBusinessService.payOrder(order.getUser(), order.getTotal());
        }
        return order;
    }

    public Order updateOrderStatus(Long orderId) {
        Order order = orderBusinessService.findById(orderId);
        if (order != null) {
            order = updateOrderStatus(order);
        }
        return order;
    }

    public Collection<Flower> getFlowers(Collection<Long> flowersIds) {
        return flowerBusinessService.getFlowers(flowersIds);
    }
}

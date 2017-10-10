package com.accenture.flowershop.be.business;

import com.accenture.flowershop.be.business.flower.FlowerBusinessService;
import com.accenture.flowershop.be.business.order.OrderBusinessService;
import com.accenture.flowershop.be.business.user.UserBusinessService;
import com.accenture.flowershop.be.entity.flower.Flower;
import com.accenture.flowershop.be.entity.order.Order;
import com.accenture.flowershop.be.entity.order.OrderStatus;
import com.accenture.flowershop.be.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class MainBusinessServiceImpl implements MainBusinessService {
    @Autowired
    private FlowerBusinessService flowerBusinessService;

    @Autowired
    private OrderBusinessService orderBusinessService;

    @Autowired
    private UserBusinessService userBusinessService;

    @Override
    public Flower orderFlower(Flower flower, int amount) {
        return flowerBusinessService.order(flower, amount);
    }

    public void cancelOrder(Order order) {
        // TODO: cancel order
    }

    public void cancelOrder(Long orderId) {
        cancelOrder(orderBusinessService.findById(orderId));
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
        return updateOrderStatus(orderBusinessService.findById(orderId));
    }

    public Collection<Flower> getFlowers(Collection<Long> flowersIds) {
        return flowerBusinessService.getFlowers(flowersIds);
    }

    public List<Order> getUserOrders(User user) {
        return orderBusinessService.getUserOrders(user);
    }
}

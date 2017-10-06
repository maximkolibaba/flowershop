package com.accenture.flowershop.be.business.order;

import com.accenture.flowershop.be.access.flower.FlowerRepository;
import com.accenture.flowershop.be.access.order.OrderItemRepository;
import com.accenture.flowershop.be.access.order.OrderRepository;
import com.accenture.flowershop.be.entity.order.Order;
import com.accenture.flowershop.be.entity.order.OrderItem;
import com.accenture.flowershop.be.entity.order.OrderStatus;
import com.accenture.flowershop.be.entity.user.User;
import com.accenture.flowershop.fe.Cart;
import com.accenture.flowershop.fe.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderBusinessServiceImpl implements OrderBusinessService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private FlowerRepository flowerRepository;

    public List<Order> getAllOrders() {
        return (List) orderRepository.findAll();
    }

    public Order createOrder(Cart cart, User user) {
        Order order = orderRepository.save(new Order(user, cart.getTotal()));
        if (order == null) {
            throw new NullPointerException();
        }

        for (CartItem cartItem : cart) {
            OrderItem orderItem = new OrderItem(order, cartItem);
            if (orderItemRepository.save(orderItem) == null) {
                return null;
            }
        }

        return order;
    }

    public boolean cancelOrder(Order order) {
        List<OrderItem> items = (List) orderItemRepository.findByOrder(order);
        for (OrderItem item : items) {
            try {
                item.getFlower().setAmount(item.getFlower().getAmount() + item.getAmount());
                flowerRepository.save(item.getFlower());
                orderItemRepository.delete(item);
            } catch (Exception e) {
                return false;
            }
        }

        orderRepository.delete(order);
        return true;
    }

    public Order setStatus(Order order, OrderStatus status) {
        order.setOrderStatus(status);
        if (status == OrderStatus.COMPLETED) {
            order.setCompleteDate(new Date());
        }
        return orderRepository.save(order);
    }

    public List<Order> getUserOrders(User user) {
        return (List) orderRepository.findByUser(user);
    }
}

package com.accenture.flowershop.be.business.order;

import com.accenture.flowershop.be.access.flower.FlowerDAO;
import com.accenture.flowershop.be.access.order.OrderDAO;
import com.accenture.flowershop.be.access.order.OrderItemDAO;
import com.accenture.flowershop.be.access.user.UserDAO;
import com.accenture.flowershop.be.entity.flower.Flower;
import com.accenture.flowershop.be.entity.order.Order;
import com.accenture.flowershop.be.entity.order.OrderItem;
import com.accenture.flowershop.be.entity.order.OrderStatus;
import com.accenture.flowershop.be.entity.user.User;
import com.accenture.flowershop.fe.Cart;
import com.accenture.flowershop.fe.CartItem;
import com.sun.deploy.security.MSCryptoDSASignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.swing.tree.VariableHeightLayoutCache;
import java.util.Date;
import java.util.List;

@Service
public class OrderBusinessServiceImpl implements OrderBusinessService {
    @Autowired
    private OrderDAO orderDAO;

    @Autowired
    private OrderItemDAO orderItemDAO;

    @Autowired
    private FlowerDAO flowerDAO;

    public List<Order> getAllOrders() {
        return orderDAO.getAll();
    }

    public Order createOrder(Cart cart, User user) {
        Order order = orderDAO.create(new Order(user, cart.getTotal()));
        if (order == null) {
            throw new NullPointerException();
        }

        for (CartItem cartItem : cart) {
            OrderItem orderItem = new OrderItem(order, cartItem);
            if (orderItemDAO.create(orderItem) == null) {
                return null;
            }
        }

        return order;
    }

    public boolean cancelOrder(Order order) {
        List<OrderItem> items = orderItemDAO.getByOrder(order);
        for (OrderItem item : items) {
            try {
                item.getFlower().setAmount(item.getFlower().getAmount() + item.getAmount());
                flowerDAO.update(item.getFlower());
                orderItemDAO.delete(item);
            } catch (Exception e) {
                return false;
            }
        }

        return orderDAO.delete(order);
    }

    public Order setStatus(Order order, OrderStatus status) {
        order.setOrderStatus(status);
        if (status == OrderStatus.COMPLETED) {
            order.setCompleteDate(new Date());
        }
        return orderDAO.update(order);
    }

    public List<Order> getUserOrders(User user) {
        return orderDAO.getByUser(user);
    }
}

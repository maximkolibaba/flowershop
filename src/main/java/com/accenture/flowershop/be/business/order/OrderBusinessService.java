package com.accenture.flowershop.be.business.order;

//    todo make order repository not dao or mb later

import com.accenture.flowershop.be.entity.flower.Flower;
import com.accenture.flowershop.be.entity.order.Order;
import com.accenture.flowershop.be.entity.user.User;
import com.accenture.flowershop.fe.Cart;

import java.util.List;

public interface OrderBusinessService {
    Order makeOrder(Cart cart, User user);
}

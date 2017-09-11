package com.accenture.flowershop.be.business.order;

import com.accenture.flowershop.be.access.order.OrderItemDAO;
import com.accenture.flowershop.be.entity.order.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemBusinessServiceImpl implements OrderItemBusinessService {
    @Autowired
    private OrderItemDAO orderItemDAO;


    public OrderItem createItem() {
        return null;
    }
}

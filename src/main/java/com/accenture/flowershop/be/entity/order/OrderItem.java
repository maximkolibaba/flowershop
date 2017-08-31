package com.accenture.flowershop.be.entity.order;

import com.accenture.flowershop.be.entity.flower.Flower;

import javax.persistence.*;

@Entity
public class OrderItem {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "orderId")
    private Order order;    //private Long orderId;

    @ManyToOne
    @JoinColumn(name = "flowerId")
    private Flower flower;  //private Long flowerId;

    private int amount;

    public OrderItem() { }
}

package com.accenture.flowershop.be.entity.order;

import com.accenture.flowershop.be.entity.flower.Flower;

import javax.persistence.Entity;

@Entity
public class OrderItem {
    private Flower flower;
    private int amount;
}

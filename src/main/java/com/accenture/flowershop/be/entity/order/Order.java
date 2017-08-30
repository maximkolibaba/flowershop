package com.accenture.flowershop.be.entity.order;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

@Entity
public class Order {
    @Id
    @GeneratedValue
    private Long id;
    private List<OrderItem> items;
    //cymma
    //IO3epaugu
}

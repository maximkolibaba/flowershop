package com.accenture.flowershop.be.entity.order;

import com.accenture.flowershop.be.entity.flower.Flower;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @ManyToOne
    @JoinColumn(name = "orderId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @Getter
    @Setter
    private Order order;

    @ManyToOne
    @JoinColumn(name = "flowerId")
    @Getter
    @Setter
    private Flower flower;

    @Getter
    @Setter
    private int amount;

    @Getter
    @Setter
    private BigDecimal subTotal;

    public OrderItem() {
    }
}

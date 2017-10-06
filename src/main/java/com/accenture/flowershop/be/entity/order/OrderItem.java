package com.accenture.flowershop.be.entity.order;

import com.accenture.flowershop.be.entity.flower.Flower;
import com.accenture.flowershop.fe.CartItem;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "orderId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Order order;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "flowerId")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Flower flower;

    private int amount;
    private BigDecimal subTotal;

    public OrderItem() {
    }

    public OrderItem(Order order, CartItem cartItem) {
        this.order = order;
        this.flower = cartItem.getFlower();
        setAmount(cartItem.getAmount());
    }

    public void setAmount(int amount) {
        this.amount = amount;
        this.subTotal = flower.getPrice().multiply(new BigDecimal(amount));
    }

    @Override
    public boolean equals(Object o) {
        boolean flag = false;
        if (o != null && o instanceof OrderItem) {
            flag = this.id.equals(((OrderItem) o).id);
        }
        return flag;
    }
}

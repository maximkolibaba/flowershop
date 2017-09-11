package com.accenture.flowershop.be.entity.order;

import com.accenture.flowershop.be.entity.flower.Flower;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
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

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "orderId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @Getter
    @Setter
    private Order order;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "flowerId")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @Getter
    @Setter
    private Flower flower;

    @Getter
    private int amount;

    @Getter
    @Setter
    private BigDecimal subTotal;

    public OrderItem() {
    }

    public OrderItem(Order order, Flower flower, int amount) {
        this.order = order;
        this.flower = flower;
        setAmount(amount); // includes subTotal
    }

    public void setAmount(int amount) {
        this.amount = amount;
        this.subTotal = flower.getPrice().multiply(new BigDecimal(amount));
    }

    public String getFlowerName() {
        return flower.getName();
    }

    @Override
    public boolean equals(Object o) {
        boolean flag = false;

        //if (o != null && o instanceof OrderItem) {
        //    if (this.order == null) {
        //        // it is cart item which has no order
        //        flag = ((OrderItem) o).getOrder() == null
        //                && ((OrderItem) o).getFlowerName() == this.getFlowerName();
        //    } else {
        //        // it is ordered flowers
        //        flag = this.id == ((OrderItem) o).getId();
        //    }
        //}
        if (o != null && o instanceof OrderItem) {
            flag = this.id == ((OrderItem) o).id;
        }
        return flag;
    }
}

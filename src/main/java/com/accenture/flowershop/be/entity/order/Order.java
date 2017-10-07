package com.accenture.flowershop.be.entity.order;

import com.accenture.flowershop.be.entity.user.User;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@Table(name = "\"ORDER\"")
public class Order implements Comparable<Order> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    private Date createDate;
    private Date completeDate;
    private BigDecimal total;
    private OrderStatus orderStatus;

    public Order() {
    }

    public Order(User user, BigDecimal price) {
        this.user = user;
        this.createDate = new Date();
        this.completeDate = null;
        this.total = price.multiply(new BigDecimal(1 - ((double) user.getDiscount()) / 100))
                .setScale(2, BigDecimal.ROUND_HALF_UP);
        this.orderStatus = OrderStatus.PENDING_PAYMENT;
    }

    @Override
    public boolean equals(Object o) {
        boolean flag = false;
        if (o != null && o instanceof Order) {
            flag = this.id.equals(((Order) o).getId());
        }
        return flag;
    }

    public int compareTo(Order order) {
        int flag = this.orderStatus.ordinal() - order.orderStatus.ordinal();
        if (flag == 0) {
            flag = this.createDate.compareTo(order.createDate);
        }
        return flag;
    }

    public void nextStatus() {
        orderStatus = orderStatus.next();
        if (orderStatus == OrderStatus.COMPLETED) {
            completeDate = new Date();
        }
    }
}

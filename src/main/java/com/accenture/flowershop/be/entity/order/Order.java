package com.accenture.flowershop.be.entity.order;

import com.accenture.flowershop.be.entity.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userId")
    @Getter
    @Setter
    private User user;

    @Getter
    @Setter
    private Date createDate;

    @Getter
    @Setter
    private Date completeDate;

    @Getter
    @Setter
    private BigDecimal total;

    @Getter
    @Setter
    private OrderStatus orderStatus;

    public Order() {
    }
}

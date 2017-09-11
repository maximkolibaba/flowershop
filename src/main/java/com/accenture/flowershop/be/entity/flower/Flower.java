package com.accenture.flowershop.be.entity.flower;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Flower {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private BigDecimal price;

    @Getter
    @Setter
    private Integer amount;

    public Flower() {
    }

    @Override
    public boolean equals(Object o) {
        boolean flag = false;
        if (o != null && o instanceof Flower) {
            flag = this.id == ((Flower) o).getId();
        }
        return flag;
    }
}

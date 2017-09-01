package com.accenture.flowershop.be.entity.flower;

import lombok.Getter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Flower {
    @Id
    @GeneratedValue
    @Getter
    private Long id;

    @Getter
    private String name;

    @Getter
    private BigDecimal price;

    @Getter
    private Integer amount;

    public Flower() {
    }
}

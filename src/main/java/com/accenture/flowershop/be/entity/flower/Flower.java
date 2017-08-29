package com.accenture.flowershop.be.entity.flower;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity//(name = "Flower")
//@Table(name = "tb_flower")
public class Flower {
    @Id
    @GeneratedValue
//    @Getter
//    @Column(name = "id", nullable = false)
    private Long id;

//    @Getter
//    @Setter
//    @Column(name = "name", nullable = false)
    private String name;

//    @Getter
//    @Setter
//    @Column(name = "price", nullable = false)
    private BigDecimal price;

//    @Getter
//    @Setter
//    @Column(name = "amount", nullable = false)
    private Integer amount;
}

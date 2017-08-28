package com.accenture.flowershop.fe.enums.customer;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "Customer")
@Table(name = "tb_customer")
public class Customer {
    private long id;
    private String name;
    private String surname;
}

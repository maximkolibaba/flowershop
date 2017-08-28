package com.accenture.flowershop.be.access;

import com.accenture.flowershop.fe.enums.customer.Customer;

import java.util.List;

public interface CustomerDAO {
    List<Customer> findAll();
    List<Customer> findOne(Long id);
    Customer createCustomer();
}

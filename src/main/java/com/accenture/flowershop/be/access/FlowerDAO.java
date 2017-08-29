package com.accenture.flowershop.be.access;

import com.accenture.flowershop.be.entity.flower.Flower;

import java.util.List;

public interface FlowerDAO extends DAO<Flower>{
    Flower findByName(String flowerName);
    List<Flower> findByTotalPrice(double price);
}

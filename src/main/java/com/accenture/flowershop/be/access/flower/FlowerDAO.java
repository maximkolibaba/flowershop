package com.accenture.flowershop.be.access.flower;

import com.accenture.flowershop.be.access.DAO;
import com.accenture.flowershop.be.entity.flower.Flower;

import java.util.List;

public interface FlowerDAO extends DAO<Flower> {
    Flower getByName(String flowerName);
    List<Flower> getByTotalPrice(double price);
}

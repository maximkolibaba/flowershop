package com.accenture.flowershop.be.business.flower;

import com.accenture.flowershop.be.entity.flower.Flower;

import java.util.List;

public interface FlowerBusinessService {
    List<Flower> getAllFlowers();

    Flower order(Flower flower, int amount);

    Flower returnToStock(Flower flower, int amount);
}

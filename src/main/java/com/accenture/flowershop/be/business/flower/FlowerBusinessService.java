package com.accenture.flowershop.be.business.flower;

import com.accenture.flowershop.be.entity.flower.Flower;

import java.util.List;

public interface FlowerBusinessService {
    List<Flower> getAllFlowers();
    Flower getById(long id);
    int flowersCount();
    boolean haveAmount(long id, int amount);
}

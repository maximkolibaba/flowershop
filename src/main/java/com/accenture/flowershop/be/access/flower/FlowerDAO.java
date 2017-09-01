package com.accenture.flowershop.be.access.flower;

import com.accenture.flowershop.be.entity.flower.Flower;

import java.util.List;

public interface FlowerDAO {
    List<Flower> getAll();
    Flower getById(Long id);
    Flower update(Flower flower);
}

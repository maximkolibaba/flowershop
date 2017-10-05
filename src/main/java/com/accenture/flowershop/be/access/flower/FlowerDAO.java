package com.accenture.flowershop.be.access.flower;

import com.accenture.flowershop.be.entity.flower.Flower;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Deprecated
public interface FlowerDAO {
    List<Flower> getAll();

    Flower getById(Long id);

    @Transactional
    Flower update(Flower flower);
}

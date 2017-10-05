package com.accenture.flowershop.be.business.flower;

import com.accenture.flowershop.be.access.flower.FlowerRepository;
import com.accenture.flowershop.be.entity.flower.Flower;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FlowerBusinessServiceImpl implements FlowerBusinessService {
    @Autowired
    FlowerRepository repository;

    public List<Flower> getAllFlowers() {
        return (List) repository.findAll();
    }

    public Flower order(Flower flower, int amount) {
        flower.setAmount(flower.getAmount() - amount);
//        return dao.update(flower);
        return repository.save(flower);
    }

    public Flower returnToStock(Flower flower, int amount) {
        return this.order(flower, -amount);
    }
}

package com.accenture.flowershop.be.business.flower;

import com.accenture.flowershop.be.access.flower.FlowerDAO;
import com.accenture.flowershop.be.entity.flower.Flower;
import org.omg.DynamicAny.DynAnyOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FlowerBusinessServiceImpl implements FlowerBusinessService {
    @Autowired
    FlowerDAO dao;

    public List<Flower> getAllFlowers() {
        return dao.getAll();
    }

    public Flower getById(long id) {
        return dao.getById(id);
    }

    public int flowersCount() {
        return dao.getAll().size();
    }

    public boolean haveAmount(long id, int amount) {
        Flower flower = dao.getById(id);
        return flower != null && flower.getAmount() >= amount;
    }

    public Flower order(Flower flower, int amount) {
        flower.setAmount(flower.getAmount() - amount);
        return dao.update(flower);
    }

//    public Flower order(long id, int amount) {
//        if ()
//    }
}

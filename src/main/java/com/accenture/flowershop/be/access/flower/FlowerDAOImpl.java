package com.accenture.flowershop.be.access.flower;

import com.accenture.flowershop.be.entity.flower.Flower;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;

@Component
public class FlowerDAOImpl implements FlowerDAO {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Flower> getAll() {
        try {
            TypedQuery<Flower> query = entityManager.createQuery("select f from Flower f", Flower.class);
            List<Flower> flowers = query.getResultList();
            return flowers;
        } catch (NoResultException ex) {
            return null;
        }
    }

    public Flower getById(Long id) {
        try {
            TypedQuery<Flower> query = entityManager.createQuery("select f from Flower f where f.id=:id", Flower.class);
            query.setParameter("id", id);
            Flower flower = query.getSingleResult();
            return flower;
        } catch (NoResultException ex) {
            return null;
        }
    }

    public Flower update(Flower flower) {
        try {
            return entityManager.merge(flower);
        } catch (NoResultException ex) {
            return null;
        }
    }
}

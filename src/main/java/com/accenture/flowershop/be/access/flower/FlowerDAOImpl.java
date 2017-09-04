package com.accenture.flowershop.be.access.flower;

import com.accenture.flowershop.be.entity.flower.Flower;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class FlowerDAOImpl implements FlowerDAO {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Flower> getAll() {
        TypedQuery<Flower> query = entityManager.createQuery("select f from Flower f", Flower.class);
        List<Flower> flowers = query.getResultList();

        return flowers;
    }

    public Flower getById(Long id) {
        TypedQuery<Flower> query = entityManager.createQuery("select f from Flower f where f.id=:id", Flower.class);
        query.setParameter("id", id);
        Flower flower = query.getSingleResult();

        return flower;
    }

    public Flower update(Flower flower) {
        try {
            entityManager.merge(flower);
            return flower;
        } catch (NoResultException ex) {
            return null;
        }
    }
}

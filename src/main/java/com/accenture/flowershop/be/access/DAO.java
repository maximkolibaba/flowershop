package com.accenture.flowershop.be.access;

import java.util.List;

public interface DAO<E> {
    List<E> getAll();
    E getById(Long id);
    boolean create(E entity);
    E update(E entity);
//    boolean update(List<E> entities);
    boolean delete(E entity);
//    boolean delete(List<E> entities);
}

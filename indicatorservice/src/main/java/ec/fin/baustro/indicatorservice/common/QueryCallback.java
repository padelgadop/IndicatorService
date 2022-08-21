package ec.fin.baustro.indicatorservice.common;

import javax.persistence.EntityManager;

@FunctionalInterface
public interface QueryCallback<T> {

    T doWithEntityManager(EntityManager entityManager);
}
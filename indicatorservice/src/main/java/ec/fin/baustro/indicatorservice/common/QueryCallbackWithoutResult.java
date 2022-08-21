package ec.fin.baustro.indicatorservice.common;

import javax.persistence.EntityManager;

public abstract class QueryCallbackWithoutResult<T> implements QueryCallback<T> {

    @Override
    public T doWithEntityManager(EntityManager entityManager) {
        doWithEntityManagerWithoutResult(entityManager);
        return null;
    }

    protected abstract void doWithEntityManagerWithoutResult(EntityManager entityManager);
}
package ec.fin.baustro.indicatorservice.dao;

import ec.fin.baustro.indicatorservice.common.QueryCallback;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.List;

public class AsimioSimpleJpaRepository<E, ID extends Serializable> extends SimpleJpaRepository<E, ID>
        implements AsimioJpaRepository {

    private final EntityManager entityManager;

    public AsimioSimpleJpaRepository(JpaEntityInformation<E, ID> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public <T> List<T> insert(QueryCallback<List<T>> callback) {
        return callback.doWithEntityManager(this.entityManager);
    }


    protected static long executeCountQuery(TypedQuery<Long> query) {
        Assert.notNull(query, "TypedQuery must not be null!");

        List<Long> totals = query.getResultList();
        long total = 0L;

        for (Long element : totals) {
            total += element == null ? 0 : element;
        }

        return total;
    }
}
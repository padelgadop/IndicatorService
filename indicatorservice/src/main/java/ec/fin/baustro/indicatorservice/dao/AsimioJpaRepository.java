package ec.fin.baustro.indicatorservice.dao;

import ec.fin.baustro.indicatorservice.common.QueryCallback;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface AsimioJpaRepository {

    <T> List<T> insert(QueryCallback<List<T>> callback);

}
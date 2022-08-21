package ec.fin.baustro.indicatorservice.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void DynamicRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insert() {
        jdbcTemplate.update("INSERT INTO BOOK (name, description) VALUES ('book name', 'book description')");
    }
}
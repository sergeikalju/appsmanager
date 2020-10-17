package sk.appsmanager.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import sk.appsmanager.dao.CrudDao;

public abstract class AbstractCrudDaoImpl<E> implements CrudDao<E> {
    private final String saveQuery;
    private final String findByIdQuery;
    private final String findByNameQuery;
    private final JdbcTemplate jdbcTemplate;

    public AbstractCrudDaoImpl(JdbcTemplate jdbcTemplate, String saveQuery, String findByIdQuery, String findByNameQuery) {
        this.jdbcTemplate = jdbcTemplate;
        this.saveQuery = saveQuery;
        this.findByIdQuery = findByIdQuery;
        this.findByNameQuery = findByNameQuery;
    }

    @Override
    public int save(E entity) {
        return this.jdbcTemplate.update(saveQuery, getSaveArgs(entity));
    }

    @Override
    public Optional<E> findByName(String name) {
        try {
            return Optional.ofNullable(this.jdbcTemplate.queryForObject(findByNameQuery, entityRowMapper, name));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }    
    }

    @Override
    public Optional<E> findById(Integer id) {
        try {
            return Optional.ofNullable(this.jdbcTemplate.queryForObject(findByIdQuery, entityRowMapper, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }  
    }

    @Override
    public List<E> findAllByName(String name) {
        // TODO Auto-generated method stub
        return null;
    }

    protected final RowMapper<E> entityRowMapper = (resultSet, rowNum) -> getEntity(resultSet).get();

    protected abstract Object[] getSaveArgs(E entity);

    protected abstract Optional<E> getEntity(ResultSet resultSet) throws SQLException;
}

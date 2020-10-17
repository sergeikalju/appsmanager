package sk.appsmanager.dao;

import java.util.List;
import java.util.Optional;

public interface CrudDao<E> {

    int save(E entity);

    Optional<E> findById(Integer id);

    Optional<E> findByName(String name);
    
    List<E> findAllByName(String name);
}

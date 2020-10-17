package sk.appsmanager.service;

public interface CrudService<E> {
    
    int register(E entity);

    E getById(Integer id);
}

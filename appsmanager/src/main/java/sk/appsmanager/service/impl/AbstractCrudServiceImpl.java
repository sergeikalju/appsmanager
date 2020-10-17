package sk.appsmanager.service.impl;

import sk.appsmanager.dao.CrudDao;
import sk.appsmanager.exception.EntityNotFoundException;
import sk.appsmanager.service.CrudService;
import sk.appsmanager.service.validator.Validator;

public abstract class AbstractCrudServiceImpl<E> implements CrudService<E> {
    private final CrudDao<E> crudDao;
    private final Validator<E> validator;

    public AbstractCrudServiceImpl(CrudDao<E> crudDao, Validator<E> validator) {
        this.crudDao = crudDao;
        this.validator = validator;
    }

    @Override
    public int register(E entity) {
        validator.validate(entity);
        return crudDao.save(entity);
    }

    @Override
    public E getById(Integer id) {
        validator.validateId(id);
        
        return crudDao.findById(id).orElseThrow(() -> new EntityNotFoundException(
                "Entity with id " + id + " is not found."));
    }
}

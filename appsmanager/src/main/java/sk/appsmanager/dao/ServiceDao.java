package sk.appsmanager.dao;

import java.util.List;

import sk.appsmanager.domain.Service;

public interface ServiceDao extends CrudDao<Service> {

    List<Service> findAllByAppCode(Integer appCode);
}

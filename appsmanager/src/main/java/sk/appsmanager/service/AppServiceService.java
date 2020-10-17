package sk.appsmanager.service;

import java.util.List;

import sk.appsmanager.domain.Service;

public interface AppServiceService extends CrudService<Service> {

    List<Service> getAllByAppCode(Integer appCode);
}

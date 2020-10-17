package sk.appsmanager.service.impl;

import java.util.List;

import sk.appsmanager.dao.ServiceDao;
import sk.appsmanager.domain.Service;
import sk.appsmanager.service.AppServiceService;
import sk.appsmanager.service.validator.ServiceValidator;

public class AppServiceServiceImpl extends AbstractCrudServiceImpl<Service> implements AppServiceService {
    private final ServiceDao serviceDao;

    public AppServiceServiceImpl(ServiceDao serviceDao, ServiceValidator validator) {
        super(serviceDao, validator);
        this.serviceDao = serviceDao;
    }

    @Override
    public List<Service> getAllByAppCode(Integer appCode) {
        return serviceDao.findAllByAppCode(appCode);
    }

}

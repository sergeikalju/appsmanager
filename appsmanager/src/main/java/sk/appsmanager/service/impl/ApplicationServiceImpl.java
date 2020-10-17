package sk.appsmanager.service.impl;

import sk.appsmanager.dao.ApplicationDao;
import sk.appsmanager.domain.Application;
import sk.appsmanager.service.ApplicationService;
import sk.appsmanager.service.validator.ApplicationValidator;

public class ApplicationServiceImpl extends AbstractCrudServiceImpl<Application> implements ApplicationService {
    
    public ApplicationServiceImpl(ApplicationDao applciationDao, ApplicationValidator validator) {
        super(applciationDao, validator);
    }
}

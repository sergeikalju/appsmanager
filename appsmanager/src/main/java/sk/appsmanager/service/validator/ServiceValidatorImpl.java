package sk.appsmanager.service.validator;

import sk.appsmanager.domain.Service;

public class ServiceValidatorImpl extends AbstractValidator<Service> implements ServiceValidator {
    
    @Override
    public void validate(Service service) {
        if (service.getAppCode() != null && service.getAppCode() < 1) {
            throw new IllegalArgumentException("App code is less than 1.");
        }
        
        if (service.getName().isEmpty()) {
            throw new IllegalArgumentException("Service name is empty.");
        }
        
        if (service.getDescription().isEmpty()) {
            throw new IllegalArgumentException("Service description is empty.");
        }
    }
}

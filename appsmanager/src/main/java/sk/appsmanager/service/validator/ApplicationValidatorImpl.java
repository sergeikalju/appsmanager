package sk.appsmanager.service.validator;

import sk.appsmanager.domain.Application;

public class ApplicationValidatorImpl extends AbstractValidator<Application> implements ApplicationValidator {

    @Override
    public void validate(Application application) {
        if (application.getName().isEmpty()) {
            throw new IllegalArgumentException("Application name is empty.");
        }
        
        if (application.getAppGroup() != null && application.getAppGroup() < 1) {
            throw new IllegalArgumentException("App group is less than 1.");
        }
        
        if (application.getDescription().isEmpty()) {
            throw new IllegalArgumentException("App description is empty.");
        }
    }

}

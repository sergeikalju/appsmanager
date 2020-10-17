package sk.appsmanager.provider;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import sk.appsmanager.domain.Application;
import sk.appsmanager.domain.Service;

@Component
public class ViewProvider {
    private static final String NEW_LINE_HTML = "<br>";
    
    public String provideAppsSearchResult(List<Application> applications) {
        return applications.stream()
                .map(app -> 
                String.format("<a href=app%d >%s</a>", app.getAppCode(), app.getName() + "&emsp;" + app.getDescription()))
                .collect(Collectors.joining(NEW_LINE_HTML));
    }
    
    public String provideServicesSearchResult(List<Service> services) {
        return services.stream()
                .map(service -> 
                String.format("<a href=service%d >%s</a>", service.getAppCode(), 
                        service.getName() + "\t" + service.getDescription()))
                .collect(Collectors.joining(NEW_LINE_HTML));
    }
    
    public String provideServicesResult(List<Service> services, Integer appCode) {
        return (String.format("List of services for Application with code %d:%s", appCode, NEW_LINE_HTML))
                .concat(services.stream()
                .map(Service::toString).
                collect(Collectors.joining(NEW_LINE_HTML)));
    }

    public String provideApplicationResult(Application application, Integer serviceCode) {
        return String.format("Application using service with code %d:%s", serviceCode, NEW_LINE_HTML)
                .concat(application.toString());
    }
}

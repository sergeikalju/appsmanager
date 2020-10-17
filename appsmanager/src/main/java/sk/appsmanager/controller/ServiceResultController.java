package sk.appsmanager.controller;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import sk.appsmanager.SpringConfiguration;
import sk.appsmanager.domain.Application;
import sk.appsmanager.provider.ViewProvider;
import sk.appsmanager.service.ApplicationService;

@Controller
public class ServiceResultController {
    private final ApplicationService applicationService;
    private final ViewProvider viewProvider;
    private final ModelAndView modelView;

    public ServiceResultController() {
        try (AnnotationConfigApplicationContext context = 
                new AnnotationConfigApplicationContext(SpringConfiguration.class)) {
            this.applicationService = context.getBean("applicationService", ApplicationService.class);
            this.viewProvider = context.getBean("getViewProvider", ViewProvider.class);
            this.modelView = context.getBean("getModelAndView", ModelAndView.class);
        }
    }

    @GetMapping("service{serviceCode}")
    @ResponseBody
    public ModelAndView getService(@PathVariable("serviceCode") String serviceCode) {
        Application application = applicationService.getById(Integer.parseInt(serviceCode));
        String app = viewProvider.provideApplicationResult(application, Integer.parseInt(serviceCode));
        modelView.addObject("result", app);
        modelView.setViewName("appInfo.jsp");

        return modelView;
    }
}

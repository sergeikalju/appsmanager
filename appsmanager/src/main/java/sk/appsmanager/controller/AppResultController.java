package sk.appsmanager.controller;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import sk.appsmanager.SpringConfiguration;
import sk.appsmanager.domain.Service;
import sk.appsmanager.provider.ViewProvider;
import sk.appsmanager.service.AppServiceService;

@Controller
public class AppResultController {
    private final AppServiceService appServiceService;
    private final ViewProvider viewProvider;
    private final ModelAndView modelView;

    public AppResultController() {
        try (AnnotationConfigApplicationContext context = 
                new AnnotationConfigApplicationContext(SpringConfiguration.class)) {
            this.appServiceService = context.getBean("appServiceService", AppServiceService.class);
            this.viewProvider = context.getBean("getViewProvider", ViewProvider.class);
            this.modelView = context.getBean("getModelAndView", ModelAndView.class);
        }
    }

    @GetMapping("app{appCode}")
    @ResponseBody
    public ModelAndView getApp(@PathVariable("appCode") String appCode) {
        List<Service> services = appServiceService.getAllByAppCode(Integer.parseInt(appCode));
        String servicesView = viewProvider.provideServicesResult(services, Integer.parseInt(appCode));
        modelView.addObject("result", servicesView);
        modelView.setViewName("appInfo.jsp");

        return modelView;
    }
}

package sk.appsmanager.controller;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import sk.appsmanager.SpringConfiguration;
import sk.appsmanager.dao.ServiceDao;
import sk.appsmanager.domain.Service;
import sk.appsmanager.provider.ViewProvider;

@Controller
public class FindServiceController {
    private final ServiceDao serviceDao;
    private final ViewProvider viewProvider;
    private final ModelAndView modelView;

    public FindServiceController() {
        try (AnnotationConfigApplicationContext context = 
                new AnnotationConfigApplicationContext(SpringConfiguration.class)) {
            this.serviceDao = context.getBean("serviceDaoSolr", ServiceDao.class);
            this.viewProvider = context.getBean("getViewProvider", ViewProvider.class);
            this.modelView = context.getBean("getModelAndView", ModelAndView.class);
        }
    }

    @RequestMapping("findService")
    public ModelAndView find(@RequestParam("name") String name) {
        List<Service> services = serviceDao.findAllByName("*" + name + "*");
        String apps = viewProvider.provideServicesSearchResult(services);
        modelView.addObject("result", apps);
        modelView.setViewName("appFind.jsp");

        return modelView;
    }
}

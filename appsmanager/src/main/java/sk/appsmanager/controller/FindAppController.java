package sk.appsmanager.controller;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import sk.appsmanager.SpringConfiguration;
import sk.appsmanager.dao.ApplicationDao;
import sk.appsmanager.domain.Application;
import sk.appsmanager.provider.ViewProvider;

@Controller
public class FindAppController {
    private final ApplicationDao applicationDao;
    private final ViewProvider viewProvider;
    private final ModelAndView modelView;

    public FindAppController() {
        try (AnnotationConfigApplicationContext context = 
                new AnnotationConfigApplicationContext(SpringConfiguration.class)) {
            this.applicationDao = context.getBean("applicationDaoSolr", ApplicationDao.class);
            this.viewProvider = context.getBean("getViewProvider", ViewProvider.class);
            this.modelView = context.getBean("getModelAndView", ModelAndView.class);

        }
    }

    @RequestMapping("findApp")
    public ModelAndView find(@RequestParam("name") String name) {
        List<Application> applications = applicationDao.findAllByName("*" + name + "*");
        String apps = viewProvider.provideAppsSearchResult(applications);
        modelView.addObject("result", apps);
        modelView.setViewName("appFind.jsp");

        return modelView;
    }
}

package sk.appsmanager.controller;

import java.io.IOException;
import java.sql.Timestamp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.params.ModifiableSolrParams;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sk.appsmanager.SpringConfiguration;
import sk.appsmanager.controller.validator.ControllerValidator;
import sk.appsmanager.domain.AppType;
import sk.appsmanager.domain.Application;
import sk.appsmanager.exception.ControllerRuntimeException;
import sk.appsmanager.service.ApplicationService;

@Controller
public class AddAppController {
    private static final Logger LOGGER = LogManager.getLogger(AddAppController.class);
    
    private final ApplicationService applicationService;
    private final ControllerValidator validator;

    public AddAppController() {
        try (AnnotationConfigApplicationContext context = 
                new AnnotationConfigApplicationContext(SpringConfiguration.class)) {
            this.applicationService = context.getBean("applicationService", ApplicationService.class);
            this.validator = context.getBean("controllerValidator", ControllerValidator.class);
        }
    }

    @RequestMapping("addApp")
    public String add(
            @RequestParam("name") String name, 
            @RequestParam("group") String group,
            @RequestParam("type") String type,
            @RequestParam("description") String description,
            @RequestParam("cost") String cost) {
        
        validator.validateGroup(group);
        validator.validateType(type);
        validator.validateCost(cost);
        
        Integer appGroup = group.isEmpty() ? null : Integer.parseInt(group);
        Double appCost = cost.isEmpty() ? null : Double.parseDouble(cost);

        Application application = Application.builder()
                .withName(name)
                .withAppGroup(appGroup)
                .withAppType(AppType.valueOf(type))
                .withDescription(description)
                .withAppCost(appCost)
                .withLastModified(new Timestamp(System.currentTimeMillis()))
                .build();

        try (AnnotationConfigApplicationContext context = 
                new AnnotationConfigApplicationContext(SpringConfiguration.class)) {
            if (applicationService.register(application) > 0 ) {
                HttpSolrClient solrClient = context.getBean("getSolrClient", HttpSolrClient.class);
                ModifiableSolrParams params = new ModifiableSolrParams();
                params.set("qt", "/dataimport");
                params.set("command", "clean");
                params.set("command", "full-import");

                solrClient.query(params);
            }
        } catch (SolrServerException | IOException e) {
            LOGGER.error("Exception stack trace: ", e);
            throw new ControllerRuntimeException("Exception stack trace: ", e);
        }

        return "index.jsp";
    }
}

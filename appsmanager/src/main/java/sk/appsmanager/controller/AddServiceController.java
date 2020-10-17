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
import sk.appsmanager.domain.Service;
import sk.appsmanager.domain.ServiceSubType;
import sk.appsmanager.domain.ServiceType;
import sk.appsmanager.exception.ControllerRuntimeException;
import sk.appsmanager.service.AppServiceService;

@Controller
public class AddServiceController {
    private static final Logger LOGGER = LogManager.getLogger(AddServiceController.class);

    private final AppServiceService appServiceService;
    private final ControllerValidator validator;

    public AddServiceController() {
        try (AnnotationConfigApplicationContext context = 
                new AnnotationConfigApplicationContext(SpringConfiguration.class)) {
            this.appServiceService = context.getBean("appServiceService", AppServiceService.class);
            this.validator = context.getBean("controllerValidator", ControllerValidator.class);
        }
    }

    @RequestMapping("addService")
    public String add(
            @RequestParam("appCode") String appCode, 
            @RequestParam("name") String name, 
            @RequestParam("type") String type,
            @RequestParam("subType") String subType,
            @RequestParam("description") String description) {

        validator.validateAppCode(appCode);
        validator.validateServiceType(type);
        validator.validateServiceSubType(subType);

        Integer code = appCode.isEmpty() ? null : Integer.parseInt(appCode);

        Service service = Service.builder()
                .withAppCode(code)
                .withName(name)
                .withType(ServiceType.valueOf(type))
                .withSubType(ServiceSubType.valueOf(subType))
                .withDescription(description)
                .withLastModified(new Timestamp(System.currentTimeMillis()))
                .build();

        try (AnnotationConfigApplicationContext context = 
                new AnnotationConfigApplicationContext(SpringConfiguration.class)) {
            if (appServiceService.register(service) > 0 ) {
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

package sk.appsmanager;

import java.util.ResourceBundle;

import javax.sql.DataSource;

import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.ModelAndView;

import sk.appsmanager.controller.validator.ControllerValidator;
import sk.appsmanager.dao.ApplicationDao;
import sk.appsmanager.dao.ServiceDao;
import sk.appsmanager.dao.impl.ApplicationDaoJdbcImpl;
import sk.appsmanager.dao.impl.ApplicationDaoSolrImpl;
import sk.appsmanager.dao.impl.ServiceDaoJdbcImpl;
import sk.appsmanager.dao.impl.ServiceDaoSolrImpl;
import sk.appsmanager.provider.ViewProvider;
import sk.appsmanager.service.AppServiceService;
import sk.appsmanager.service.ApplicationService;
import sk.appsmanager.service.impl.AppServiceServiceImpl;
import sk.appsmanager.service.impl.ApplicationServiceImpl;
import sk.appsmanager.service.validator.ApplicationValidator;
import sk.appsmanager.service.validator.ApplicationValidatorImpl;
import sk.appsmanager.service.validator.ServiceValidator;
import sk.appsmanager.service.validator.ServiceValidatorImpl;

@Configuration
public class SpringConfiguration {
     private static final String DATASOURCE_PROPERTIES = "jdbc"; 
     private static final String SOLR_URL = "http://localhost:8983/solr/test_core";

    @Bean
    public DataSource getDataSource() {
        ResourceBundle resource = ResourceBundle.getBundle(DATASOURCE_PROPERTIES);
        
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl(resource.getString("jdbc.url"));
        dataSource.setUsername(resource.getString("jdbc.username"));
        dataSource.setPassword(resource.getString("jdbc.password"));
        
        return dataSource;
    }
    
    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(getDataSource());
    }
    
    @Bean
    public ApplicationDao applicationDaoJdbc() {
        return new ApplicationDaoJdbcImpl(jdbcTemplate());
    }
    
    @Bean
    public ApplicationDao applicationDaoSolr() {
        return new ApplicationDaoSolrImpl();
    }
    
    @Bean
    public ServiceDao serviceDaoJdbc() {
        return new ServiceDaoJdbcImpl(jdbcTemplate());
    }
    
    @Bean
    public ServiceDao serviceDaoSolr() {
        return new ServiceDaoSolrImpl();
    }
    
    @Bean
    public HttpSolrClient getSolrClient() {
        HttpSolrClient client = new HttpSolrClient.Builder(SOLR_URL).build();
        client.setParser(new XMLResponseParser());
        
        return client;
    }
    
    @Bean
    public ViewProvider getViewProvider() {
        return new ViewProvider();
    }
    
    @Bean
    public ModelAndView getModelAndView() {
        return new ModelAndView();
    }
    
    @Bean 
    public ApplicationValidator applicationValidator() {
        return new ApplicationValidatorImpl();
    }
    
    @Bean
    public ApplicationService applicationService() {
        return new ApplicationServiceImpl(applicationDaoJdbc(), applicationValidator());
    }
    
    @Bean 
    public ServiceValidator serviceValidator() {
        return new ServiceValidatorImpl();
    }
    
    @Bean
    public AppServiceService appServiceService() {
        return new AppServiceServiceImpl(serviceDaoJdbc(), serviceValidator());
    }
    
    @Bean
    public ControllerValidator controllerValidator() {
        return new ControllerValidator();
    }
}

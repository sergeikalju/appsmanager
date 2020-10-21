package sk.appsmanager;

import java.io.IOException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.params.ModifiableSolrParams;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import sk.appsmanager.exception.ControllerRuntimeException;

@WebListener
public class ContextListener implements ServletContextListener {
    private static final Logger LOGGER = LogManager.getLogger(ContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try (AnnotationConfigApplicationContext context = 
                new AnnotationConfigApplicationContext(SpringConfiguration.class)) {
                HttpSolrClient solrClient = context.getBean("getSolrClient", HttpSolrClient.class);
                ModifiableSolrParams params = new ModifiableSolrParams();
                params.set("qt", "/dataimport");
                params.set("command", "clean");
                params.set("command", "full-import");

                solrClient.query(params);
        } catch (SolrServerException | IOException e) {
            LOGGER.error("Exception stack trace: ", e);
            throw new ControllerRuntimeException("Exception stack trace: ", e);
        }
    }
}

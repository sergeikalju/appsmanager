package sk.appsmanager.dao.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.params.MapSolrParams;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import sk.appsmanager.SpringConfiguration;
import sk.appsmanager.dao.ApplicationDao;
import sk.appsmanager.domain.Application;
import sk.appsmanager.exception.DaoRuntimeException;

public class ApplicationDaoSolrImpl implements ApplicationDao {
    private static final Logger LOGGER = LogManager.getLogger(ApplicationDaoSolrImpl.class);

    @Override
    public List<Application> findAllByName(String name) {
        List<Application> applications = new ArrayList<>();

        try (AnnotationConfigApplicationContext context = 
                new AnnotationConfigApplicationContext(SpringConfiguration.class)) {
            
            HttpSolrClient solrClient = context.getBean("getSolrClient", HttpSolrClient.class);
            final Map<String, String> queryParamMap = new HashMap<>();
            queryParamMap.put("q", "app_name:" + name);
            queryParamMap.put("start", "0");
            queryParamMap.put("rows", "1000");
            MapSolrParams queryParams = new MapSolrParams(queryParamMap);

            final QueryResponse response = solrClient.query(queryParams);
            final SolrDocumentList documents = response.getResults();
            
            applications = documents.stream().map(document -> Application.builder()
                .withAppCode(Integer.parseInt((String) document.getFirstValue("app_code")))
                .withName((String) document.getFirstValue("app_name"))
                .withDescription((String) document.getFirstValue("app_description"))
                .build()).collect(Collectors.toList());
        } catch (SolrServerException | IOException e) {
            LOGGER.error("Exception stack trace: ", e);
            throw new DaoRuntimeException("Exception stack trace: ", e);
        }

        return applications;
    }

    @Override
    public int save(Application entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<Application> findByName(String name) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<Application> findById(Integer id) {
        throw new UnsupportedOperationException();
    }
}

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
import sk.appsmanager.dao.ServiceDao;
import sk.appsmanager.domain.Service;
import sk.appsmanager.exception.DaoRuntimeException;

public class ServiceDaoSolrImpl implements ServiceDao {
    private static final Logger LOGGER = LogManager.getLogger(ServiceDaoSolrImpl.class);

    @Override
    public List<Service> findAllByName(String name) {
        List<Service> services = new ArrayList<>();

        try (AnnotationConfigApplicationContext context = 
                new AnnotationConfigApplicationContext(SpringConfiguration.class)) {

            HttpSolrClient solrClient = context.getBean("getSolrClient", HttpSolrClient.class);
            final Map<String, String> queryParamMap = new HashMap<>();
            queryParamMap.put("q", "service_name:" + name);
            queryParamMap.put("start", "0");
            queryParamMap.put("rows", "1000");
            MapSolrParams queryParams = new MapSolrParams(queryParamMap);

            final QueryResponse response = solrClient.query(queryParams);
            final SolrDocumentList documents = response.getResults();

            services = documents.stream().map(document -> Service.builder()
                    .withAppCode(Integer.parseInt((String) document.getFirstValue("app_code")))
                    .withServiceCode(Integer.parseInt((String) document.getFirstValue("service_code")))
                    .withName((String) document.getFirstValue("service_name"))
                    .withDescription((String) document.getFirstValue("service_description"))
                    .build()).collect(Collectors.toList());
        } catch (SolrServerException | IOException e) {
            LOGGER.error("Exception stack trace: ", e);
            throw new DaoRuntimeException("Exception stack trace: ", e);
        }

        return services;
    }

    @Override
    public int save(Service service) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<Service> findById(Integer id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<Service> findByName(String name) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Service> findAllByAppCode(Integer appCode) {
        throw new UnsupportedOperationException();
    }
}

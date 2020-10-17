package sk.appsmanager.controller;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import sk.appsmanager.WebConfiguration;

public class FrontController extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { 
                WebConfiguration.class 
        };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return null;
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { 
                "/" 
        };
    }
}

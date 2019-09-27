package by.epam.touragency.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        Class[] configClasses = new Class[]{AppConfig.class};
        return configClasses;
    }

    @Override
    protected String[] getServletMappings() {
        String[] servletMappings = new String[]{"/"};
        return servletMappings;
    }
}

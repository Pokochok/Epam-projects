package by.epam.touragency.config;

import by.epam.touragency.logic.UserDetailsServiceImpl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.Properties;

@Configuration
@EnableAspectJAutoProxy
@EnableTransactionManagement
@EnableWebMvc
@ComponentScan({"by.epam.touragency"})
public class AppConfig implements WebMvcConfigurer {
    @Bean
    @Autowired
    public Session session(SessionFactory sessionFactory){
        return sessionFactory.openSession();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
        sessionLocaleResolver.setDefaultLocale(new Locale("en"));
        return sessionLocaleResolver;
    }

    @Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasenames("jsp/home", "jsp/about-company", "jsp/booking", "jsp/login", "jsp/orders",
                "jsp/registration", "jsp/ticket-registration", "jsp/tickets", "jsp/timestamp", "jsp/tour-overview",
                "jsp/tour-registration", "jsp/tours", "jsp/user-profile");
        messageSource.setUseCodeAsDefaultMessage(true);
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
        interceptor.setParamName("language");
        registry.addInterceptor(interceptor);
    }

    @Bean
    public FreeMarkerConfigurer freeMarkerConfigurer() {
        FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
        configurer.setTemplateLoaderPath("");
        configurer.setDefaultEncoding("UTF-8");
        return configurer;
    }

    @Bean
    public FreeMarkerViewResolver freeMarkerViewResolver() {
        FreeMarkerViewResolver freeMarkerViewResolver = new FreeMarkerViewResolver();
        freeMarkerViewResolver.setSuffix(".ftl");
        freeMarkerViewResolver.setPrefix("/");
        freeMarkerViewResolver.setCache(false);
        freeMarkerViewResolver.setContentType("text/html;charset=UTF-8");
        return freeMarkerViewResolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/img/**")
                .addResourceLocations("/img/");
        registry
                .addResourceHandler("/uui/js/uui/**")
                .addResourceLocations("/uui/js/");
        registry
                .addResourceHandler("/uui/**")
                .addResourceLocations("/uui/");
        registry
                .addResourceHandler("/templates/**")
                .addResourceLocations("/templates/");
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() throws IOException {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        Properties properties = new Properties();
        InputStream inputStream = AppConfig.class.getClassLoader().getResourceAsStream("hibernate.properties");
        properties.load(inputStream);
        sessionFactory.setHibernateProperties(properties);
        sessionFactory.setDataSource(pgSqlDataSource());
        sessionFactory.setPackagesToScan("by.epam.touragency.entity");
        return sessionFactory;
    }

    @Bean
    public PlatformTransactionManager hibernateTransactionManager() throws IOException {
        HibernateTransactionManager transactionManager
                = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }

    @Bean
    public DataSource pgSqlDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(PropertyHolder.getInstance().getDriverName());
        dataSource.setUrl(PropertyHolder.getInstance().getUrl());
        dataSource.setUsername(PropertyHolder.getInstance().getUserName());
        dataSource.setPassword(PropertyHolder.getInstance().getPassword());
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(pgSqlDataSource());
    }

    @Bean
    public PlatformTransactionManager platformTransactionManager() {
        return new DataSourceTransactionManager(pgSqlDataSource());
    }
}

package by.epam.touragency.config;

import by.epam.touragency.entity.Order;
import by.epam.touragency.logic.BookingLogic;
import by.epam.touragency.repository.Repository;
import by.epam.touragency.repository.impl.OrderRepository;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.Enumeration;
import java.util.ResourceBundle;

@Configuration
@ComponentScan("by.epam.touragency")
public class WebAppTestContext {

    @Bean
    public BookingLogic bookingLogic(){
        return new BookingLogic();
    }

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();

        messageSource.setBasename("i18n/messages");
        messageSource.setUseCodeAsDefaultMessage(true);

        return messageSource;
    }

    @Bean
    public ResourceBundle resourceBundle(){
        return new ResourceBundle() {
            @Override
            protected Object handleGetObject(String key) {
                return "test String";
            }

            @Override
            public Enumeration<String> getKeys() {
                return Collections.emptyEnumeration();
            }
        };
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(pgSqlDataSource());
    }

    @Bean
    public PlatformTransactionManager platformTransactionManager(){
        return new DataSourceTransactionManager(pgSqlDataSource());
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
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}

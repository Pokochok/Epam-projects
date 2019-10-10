package by.epam.touragency.config;

import by.epam.touragency.logic.BookingLogic;
import org.mockito.Mockito;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.security.access.SecurityConfig;
import org.springframework.test.context.TestContext;

import java.util.Collections;
import java.util.Enumeration;
import java.util.ResourceBundle;

@Configuration
@ComponentScan("by.epam.touragency")
@Import({AppConfig.class, SecurityConfiguration.class})
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
}

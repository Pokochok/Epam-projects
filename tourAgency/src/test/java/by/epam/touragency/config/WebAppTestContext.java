package by.epam.touragency.config;

import org.mockito.Mockito;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.test.context.TestContext;

@Configuration
@ComponentScan()
@Import({AppConfig.class, SecurityConfiguration.class})
public class WebAppTestContext {

}

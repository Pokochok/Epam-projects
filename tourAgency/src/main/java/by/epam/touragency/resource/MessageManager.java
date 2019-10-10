package by.epam.touragency.resource;

import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.ResourceBundle;

@Component
public class MessageManager {

    public String getProperty(String key, Locale locale) {
        ResourceBundle bundle = ResourceBundle.getBundle("jsp/infMessages", locale);
        return bundle.getString(key);
    }
}

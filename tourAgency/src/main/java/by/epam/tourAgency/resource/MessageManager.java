package by.epam.tourAgency.resource;

import java.util.Locale;
import java.util.ResourceBundle;

public class MessageManager {

    private MessageManager() {
    }

    public static String getProperty(String key, Locale locale) {
        ResourceBundle bundle = ResourceBundle.getBundle("jsp/infMessages", locale);
        return bundle.getString(key);
    }
}

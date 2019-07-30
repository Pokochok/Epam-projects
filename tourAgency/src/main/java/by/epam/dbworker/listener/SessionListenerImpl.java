package by.epam.dbworker.listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

public class SessionListenerImpl implements HttpSessionAttributeListener {
    private static final Logger LOGGER = LogManager.getLogger();

    public void attributeRemoved(HttpSessionBindingEvent ev) {
    }

    public void attributeAdded(HttpSessionBindingEvent ev) {
        // запись в log-файл или иные действия
        LOGGER.info("add: " + ev.getClass().getSimpleName() + " : "+ ev.getName()
                + " : " + ev.getValue());
    }
    public void attributeReplaced(HttpSessionBindingEvent ev) {
        // запись в log-файл или иные действия
        LOGGER.info("replace: " + ev.getClass().getSimpleName() + " : " + ev.getName()
                + " : " + ev.getValue());
    }
}

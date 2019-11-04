package by.epam.touragency.listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;


public class SessionListenerImpl implements HttpSessionAttributeListener {
    private static final Logger LOGGER = LogManager.getLogger();

    public void attributeRemoved(HttpSessionBindingEvent ev) {
    }

    public void attributeAdded(HttpSessionBindingEvent ev) {
        LOGGER.info("add: " + ev.getClass().getSimpleName() + " : "+ ev.getName()
                + " : " + ev.getValue());
    }
    public void attributeReplaced(HttpSessionBindingEvent ev) {
        LOGGER.info("replace: " + ev.getClass().getSimpleName() + " : " + ev.getName()
                + " : " + ev.getValue());
    }
}

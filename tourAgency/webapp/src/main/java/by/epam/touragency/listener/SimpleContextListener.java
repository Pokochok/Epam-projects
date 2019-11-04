package by.epam.touragency.listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class SimpleContextListener implements ServletContextListener {
    private static final Logger LOGGER = LogManager.getLogger();

    public void contextInitialized(ServletContextEvent ev) {
        ServletContext context = ev.getServletContext();
        String mailFeedback = context.getInitParameter("feedback");
        LOGGER.info("Context Initialized with parameter: " + mailFeedback);
    }
    public void contextDestroyed(ServletContextEvent ev) {
        LOGGER.info("Context destroyed");
    }
}
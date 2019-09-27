package by.epam.touragency.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import static by.epam.touragency.util.PageMsgConstant.LOGGER;

@WebListener
public class SimpleContextListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent ev) {
        ServletContext context = ev.getServletContext();
        String mailFeedback = context.getInitParameter("feedback");
        LOGGER.info("Context Initialized with parameter: " + mailFeedback);
    }
    public void contextDestroyed(ServletContextEvent ev) {
        LOGGER.info("Context destroyed");
    }
}
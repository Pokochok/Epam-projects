package by.epam.dbworker.listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

public class SimpleRequestListener implements ServletRequestListener {
    private static final Logger LOGGER = LogManager.getLogger();

    public void requestInitialized(ServletRequestEvent event) {
// будет использован для получения информации о запросе
        HttpServletRequest req = (HttpServletRequest) event.getServletRequest();
        String uri = "Request Initialized for " + req.getRequestURI();
        String id = "Request Initialized with ID=" + req.getRequestedSessionId();
        LOGGER.info(uri + "\n" + id);
        ServletContext context = event.getServletContext();
// счетчик числа созданных запросов
        Integer reqCount = (Integer) req.getSession().getAttribute("counter");
        if (reqCount == null) {
            reqCount = 0;
        }
        context.log(uri + "\n" + id + ", Request Counter =" + reqCount);
    }

    public void requestDestroyed(ServletRequestEvent event) {
        LOGGER.info("Request Destroyed: "
                + event.getServletRequest().getAttribute("lifecycle"));
    }
}

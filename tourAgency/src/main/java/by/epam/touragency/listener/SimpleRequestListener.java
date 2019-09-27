package by.epam.touragency.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

import static by.epam.touragency.util.PageMsgConstant.LOGGER;

public class SimpleRequestListener implements ServletRequestListener {

    public void requestInitialized(ServletRequestEvent event) {
        HttpServletRequest req = (HttpServletRequest) event.getServletRequest();
        String uri = "Request Initialized for " + req.getRequestURI();
        String id = "Request Initialized with ID=" + req.getRequestedSessionId();
        LOGGER.info(uri + "\n" + id);
        ServletContext context = event.getServletContext();
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

package by.epam.touragency.controller;

import by.epam.touragency.command.ActionCommand;
import by.epam.touragency.connectionpool.ProxyConnectionPool;
import by.epam.touragency.exception.CommandException;
import by.epam.touragency.logic.DefineActionCommandLogic;
import by.epam.touragency.resource.ConfigurationManager;
import by.epam.touragency.resource.MessageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PreDestroy;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

import static by.epam.touragency.util.PageMsgConstant.*;
import static by.epam.touragency.util.PageMsgConstant.LOGGER;
import static by.epam.touragency.util.ParameterConstant.*;

/**
 * Main application servlet
 */
@Controller
@RequestMapping("/controller")
public class MainController {
    @Autowired
    private MessageManager messageManager;

    @GetMapping
    public ModelAndView doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        return processRequest(req, resp);
    }

    @PostMapping
    public ModelAndView doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        return processRequest(req, resp);
    }

    @PreDestroy
    public void destroy() {
        ProxyConnectionPool.getInstance().closePool();
    }

    /**
     * Process request
     * @param request object of HttpServletRequest type
     * @param response object of HttpServletResponse type
     * @throws IOException if forward, redirect or error sending not completed with success
     */

    private ModelAndView processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SessionRequestContent requestContent = new SessionRequestContent();
        requestContent.extractValues(request);
        ModelAndView modelAndView = new ModelAndView();
        String page = null;
        DefineActionCommandLogic defineActionCommandLogic = new DefineActionCommandLogic();
        ActionCommand command = defineActionCommandLogic.defineCommand(requestContent);
        try {
            page = command.execute(requestContent);
        } catch (CommandException e) {
            LOGGER.error("User role is not defined while updating login", e);
            response.sendError(500, e.toString());
        }

        modelAndView.addAllObjects(requestContent.getRequestAttributes());
        if (page != null && !ConfigurationManager.getProperty(INF_PAGE_FLAG).equals(page)) {
            modelAndView.setViewName(page);
        } else if (page != null && ConfigurationManager.getProperty(INF_PAGE_FLAG).equals(page)) {
            response.setStatus(HttpServletResponse.SC_SEE_OTHER);
            String headerValue = HEADER_VALUE +
                    requestContent.getAttribute(ATTR_NAME_MSG_KEY);
            response.setHeader(HEADER_NAME, headerValue);
            page = ConfigurationManager.getProperty(TO_INF_PAGE_PATH);
            modelAndView.setViewName(page);
        } else {
            page = ConfigurationManager.getProperty(WELCOME_PAGE_PATH);
            request.getSession().setAttribute(ATTR_NAME_NULL_PAGE, messageManager.getProperty(NULL_PAGE_MSG_KEY,
                    new Locale(request.getSession().getAttribute(ATTR_NAME_LANGUAGE).toString())));
            modelAndView.setViewName(page);
        }
        return modelAndView;
    }
}

package by.epam.touragency.controller;

import by.epam.touragency.command.ActionCommand;
import by.epam.touragency.connectionpool.ProxyConnectionPool;
import by.epam.touragency.exception.CommandException;
import by.epam.touragency.logic.DefineActionCommandLogic;
import by.epam.touragency.resource.ConfigurationManager;
import by.epam.touragency.resource.MessageManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PreDestroy;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
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
//@WebServlet("/controller")
@Controller
@RequestMapping("/controller")
public class MainController {

    @GetMapping
    public ModelAndView doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        return processRequest(req, resp);
    }

    @PostMapping
    public ModelAndView doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
     * @throws ServletException if forward not completed with success
     * @throws IOException if forward, redirect or error sending not completed with success
     */

    private ModelAndView processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
//        requestContent.insertAttributes(request);
        modelAndView.addAllObjects(requestContent.getRequestAttributes());
        if (page != null && !ConfigurationManager.getProperty(INF_PAGE_FLAG).equals(page)) {
//            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
//            dispatcher.forward(request, response);
            modelAndView.setViewName(page);
        } else if (page != null && ConfigurationManager.getProperty(INF_PAGE_FLAG).equals(page)) {
            response.setStatus(HttpServletResponse.SC_SEE_OTHER);
            String headerValue = HEADER_VALUE +
                    requestContent.getAttribute(ATTR_NAME_MSG_KEY);
            response.setHeader(HEADER_NAME, headerValue);
            page = ConfigurationManager.getProperty(TO_INF_PAGE_PATH);
//            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
//            dispatcher.forward(request, response);
            modelAndView.setViewName(page);
        } else {
            page = ConfigurationManager.getProperty(WELCOME_PAGE_PATH);
            request.getSession().setAttribute(ATTR_NAME_NULL_PAGE, MessageManager.getProperty(NULL_PAGE_MSG_KEY,
                    new Locale(request.getSession().getAttribute(ATTR_NAME_LANGUAGE).toString())));
            //response.sendRedirect(request.getContextPath() + page);
        }
        return modelAndView;
    }
}

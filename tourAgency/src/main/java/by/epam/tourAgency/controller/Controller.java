package by.epam.tourAgency.controller;

import by.epam.tourAgency.command.ActionCommand;
import by.epam.tourAgency.connectionpool.ProxyConnectionPool;
import by.epam.tourAgency.exception.CommandException;
import by.epam.tourAgency.logic.DefineActionCommandLogic;
import by.epam.tourAgency.resource.ConfigurationManager;
import by.epam.tourAgency.resource.MessageManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

import static by.epam.tourAgency.util.PageMsgConstant.*;
import static by.epam.tourAgency.util.PageMsgConstant.LOGGER;
import static by.epam.tourAgency.util.ParameterConstant.*;

/**
 * Main application servlet
 */
@WebServlet("/controller")
public class Controller extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    public void destroy() {
        ProxyConnectionPool.getInstance().closePool();
    }

    @Override
    public void init() throws ServletException {
        super.init();
        LOGGER.info("Servlet was initialized successfully");
    }

    /**
     * Process request
     * @param request object of HttpServletRequest type
     * @param response object of HttpServletResponse type
     * @throws ServletException if forward not completed with success
     * @throws IOException if forward, redirect or error sending not completed with success
     */
    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SessionRequestContent requestContent = new SessionRequestContent();
        requestContent.extractValues(request);

        String page = null;
        DefineActionCommandLogic defineActionCommandLogic = new DefineActionCommandLogic();
        ActionCommand command = defineActionCommandLogic.defineCommand(requestContent);
        try {
            page = command.execute(requestContent);
        } catch (CommandException e) {
            LOGGER.error("User role is not defined while updating login", e);
            response.sendError(500, e.toString());
        }
        requestContent.insertAttributes(request);

        if (page != null && !ConfigurationManager.getProperty(INF_PAGE_FLAG).equals(page)) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            dispatcher.forward(request, response);
        } else if (page != null && ConfigurationManager.getProperty(INF_PAGE_FLAG).equals(page)) {
            response.setStatus(HttpServletResponse.SC_SEE_OTHER);
            String headerValue = HEADER_VALUE +
                    requestContent.getAttribute(ATTR_NAME_MSG_KEY);
            response.setHeader(HEADER_NAME, headerValue);
            page = ConfigurationManager.getProperty(TO_INF_PAGE_PATH);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            dispatcher.forward(request, response);
        } else {
            page = ConfigurationManager.getProperty(WELCOME_PAGE_PATH);
            request.getSession().setAttribute(ATTR_NAME_NULL_PAGE, MessageManager.getProperty(NULL_PAGE_MSG_KEY,
                    new Locale(request.getSession().getAttribute(ATTR_NAME_LANGUAGE).toString())));
            response.sendRedirect(request.getContextPath() + page);
        }
    }
}

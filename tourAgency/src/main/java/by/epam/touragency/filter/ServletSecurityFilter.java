package by.epam.touragency.filter;

import by.epam.touragency.entity.Role;
import by.epam.touragency.entity.User;
import by.epam.touragency.exception.LogicException;
import by.epam.touragency.exception.RepositoryException;
import by.epam.touragency.repository.impl.UserRepository;
import by.epam.touragency.resource.ConfigurationManager;
import by.epam.touragency.specification.Specification;
import by.epam.touragency.specification.impl.admin.FindAdminByLoginSpecification;
import by.epam.touragency.specification.impl.agent.FindAgentByLoginSpecification;
import by.epam.touragency.specification.impl.client.FindClientByLoginSpecification;

import java.io.IOException;
import java.util.Set;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static by.epam.touragency.util.PageMsgConstant.MAIN_PAGE_PATH;
import static by.epam.touragency.util.ParameterConstant.*;

@WebFilter(urlPatterns = {"/controller"}, servletNames = {"MainServlet"})
public class ServletSecurityFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        String page = null;
        Object role = session.getAttribute(ATTR_NAME_USER_ROLE);
        Object login = session.getAttribute(ATTR_NAME_USER_LOGIN);
        RequestDispatcher dispatcher;
        if (role == null || (login == null && Role.GUEST != Role.valueOf(String.valueOf(role).toUpperCase()))) {
            session.setAttribute(PARAM_NAME_ROLE, Role.GUEST);
            page = ConfigurationManager.getProperty(MAIN_PAGE_PATH);
            dispatcher = request.getServletContext().getRequestDispatcher(page);
            dispatcher.forward(req, resp);
            return;
        }
        dispatcher = null;

        if (login == null){
            chain.doFilter(request, response);
            return;
        }

        Role userRole = null;
        try {
            userRole = getByLogin(String.valueOf(login)).getRole();
        } catch (LogicException e) {
            session.setAttribute(ATTR_NAME_USER_LOGIN, null);
            resp.sendError(500, e.toString());
            return;
        }

        if (!userRole.equals(Role.valueOf(String.valueOf(role).toUpperCase()))) {
            session.setAttribute(ATTR_NAME_USER_LOGIN, null);
            resp.sendError(500, "Illegal access to role");
            return;
        }
        chain.doFilter(request, response);
    }

    private static User getByLogin(String enterLogin) throws LogicException {
        User user = null;
        UserRepository repository = UserRepository.getInstance();
        Specification clientSpecification = new FindClientByLoginSpecification(enterLogin);
        Specification agentSpecification = new FindAgentByLoginSpecification(enterLogin);
        Specification adminSpecification = new FindAdminByLoginSpecification(enterLogin);

        try {
            Set<User> users;
            if (!(users = repository.query(clientSpecification)).isEmpty()) {
                user = users.iterator().next();
            } else if (!(users = repository.query(agentSpecification)).isEmpty()) {
                user = users.iterator().next();
            } else if (!(users = repository.query(adminSpecification)).isEmpty()) {
                user = users.iterator().next();
            }
        } catch (RepositoryException e) {
            throw new LogicException(e);
        }
        return user;
    }

    public void init(FilterConfig fConfig) throws ServletException {
    }
}
package by.epam.touragency.handler;

import by.epam.touragency.controller.LoginCommand;
import by.epam.touragency.resource.ConfigurationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

import java.util.Locale;

import static by.epam.touragency.util.PageMsgConstant.*;
import static by.epam.touragency.util.ParameterConstant.ATTR_NAME_MSG_KEY;

@ControllerAdvice
public class ExceptionHandlerController {
    @Autowired
    private LoginCommand loginCommand;

    @ExceptionHandler(Throwable.class)
    public ModelAndView handleError(HttpServletRequest req, Exception ex) {
        ModelAndView modelAndView = new ModelAndView();
        ex.printStackTrace();
        modelAndView.addObject("exception", ex);
        modelAndView.addObject("url", req.getRequestURL());
        modelAndView.setViewName(ConfigurationManager.getProperty(ERROR_PAGE_PATH));
        return modelAndView;
    }

    @ExceptionHandler(DataAccessException.class)
    public ModelAndView dataAccessHandler() {
        String page = "redirect:" + ConfigurationManager.getProperty(INF_URL_PATH) + "?" + ATTR_NAME_MSG_KEY + "=" + OPERATION_NOT_SUCCESS_MSG_KEY;
        return new ModelAndView(page);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ModelAndView accessDeniedHandler() {
        String page = "redirect:" + ConfigurationManager.getProperty(INF_URL_PATH) + "?" + ATTR_NAME_MSG_KEY + "=" + ACCESS_DENIED_ERROR_MSG_KEY;
        return new ModelAndView(page);
    }
}
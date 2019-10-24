package by.epam.touragency.controller;

import by.epam.touragency.exception.ControllerException;
import by.epam.touragency.resource.ConfigurationManager;
import by.epam.touragency.resource.MessageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.util.Locale;

import static by.epam.touragency.util.PageMsgConstant.*;
import static by.epam.touragency.util.ParameterConstant.*;

@Controller
public class LoginCommand {
    @Autowired
    private MessageManager messageManager;

    @RequestMapping(value = "/fail_login")
    public ModelAndView execute(@SessionAttribute(value = "language", required = false) Locale language)
            throws ControllerException {
        if (language == null) {
            language = new Locale(EN_LOCALE);
        }
        LOGGER.debug("Incorrect login or password");
        String page = ConfigurationManager.getProperty(HOME_PAGE_PATH);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(ATTR_NAME_ERROR_LOGIN, messageManager.getProperty(LOGIN_ERROR_MSG_KEY, language));
        modelAndView.setViewName(page);
        return modelAndView;
    }
}

package by.epam.touragency.controller;

import by.epam.touragency.resource.ConfigurationManager;
import by.epam.touragency.resource.MessageManager;
import by.epam.touragency.util.PageMsgConstant;
import by.epam.touragency.util.ParameterConstant;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.util.Locale;

@Controller
public class LoginCommand {
    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private MessageManager messageManager;

    @RequestMapping(value = "/fail_login")
    public ModelAndView execute(@SessionAttribute(value = "language", required = false) Locale language){
        if (language == null) {
            language = new Locale(ParameterConstant.EN_LOCALE);
        }
        LOGGER.debug("Incorrect login or password");
        String page = ConfigurationManager.getProperty(PageMsgConstant.HOME_PAGE_PATH);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(ParameterConstant.ATTR_NAME_ERROR_LOGIN, messageManager.getProperty(PageMsgConstant.LOGIN_ERROR_MSG_KEY, language));
        modelAndView.setViewName(page);
        return modelAndView;
    }
}

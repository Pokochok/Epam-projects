package by.epam.touragency.controller;

import by.epam.touragency.entity.UserPrincipal;
import by.epam.touragency.exception.ControllerException;
import by.epam.touragency.exception.LogicException;
import by.epam.touragency.logic.UpdateUserLogic;
import by.epam.touragency.resource.ConfigurationManager;
import by.epam.touragency.resource.MessageManager;
import by.epam.touragency.util.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.util.Locale;

import static by.epam.touragency.util.PageMsgConstant.*;
import static by.epam.touragency.util.ParameterConstant.*;

@Controller
public class ChangeLoginCommand {
    @Autowired
    private UpdateUserLogic updateUserLogic;

    @Autowired
    private MessageManager messageManager;

    @Autowired
    private Validation validation;

    @Secured({"ROLE_ADMIN", "ROLE_AGENT", "ROLE_CLIENT"})
    @PostMapping("/change_login")
    public ModelAndView execute(
            @RequestParam(value = PARAM_NAME_NEW_LOGIN) String login,
            @SessionAttribute(value = ATTR_NAME_LANGUAGE, required = false) Locale language
    ) throws ControllerException {
        if (language == null) {
            language = new Locale(EN_LOCALE);
        }
        if (!updateUserLogic.checkPrincipal()) {
            return new ModelAndView(ConfigurationManager.getProperty(USER_PROFILE_PAGE_PATH));
        }
        UserPrincipal userDetails = updateUserLogic.getUserPrincipal();
        String role = userDetails.getUserRole().toString();
        String email = userDetails.getUserEmail();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(ConfigurationManager.getProperty(USER_PROFILE_PAGE_PATH));
        if (!validation.validateLogin(login)) {
            modelAndView.addObject(ATTR_NAME_ERROR_CHANGE_LOGIN,
                    messageManager.getProperty(CHANGE_LOGIN_ERROR_MSG_KEY, language));
            return modelAndView;
        }

        try {
            if (updateUserLogic.updateLogin(role, login, email)) {
                modelAndView.addObject(ATTR_NAME_USER_LOGIN, login);
            } else {
                modelAndView.addObject(ATTR_NAME_ERROR_LOGIN_EXISTS,
                        messageManager.getProperty(LOGIN_EXISTS_MSG_KEY, language));
            }
        } catch (LogicException e) {
            throw new ControllerException(e);
        }
        return modelAndView;
    }
}

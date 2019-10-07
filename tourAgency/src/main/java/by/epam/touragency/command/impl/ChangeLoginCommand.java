package by.epam.touragency.command.impl;

import by.epam.touragency.exception.CommandException;
import by.epam.touragency.exception.LogicException;
import by.epam.touragency.logic.UpdateUserLogic;
import by.epam.touragency.resource.ConfigurationManager;
import by.epam.touragency.resource.MessageManager;
import by.epam.touragency.util.Validation;
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
    @Secured({"ROLE_ADMIN", "ROLE_AGENT", "ROLE_CLIENT"})
    @PostMapping("/change_login")
    public ModelAndView execute(
            @SessionAttribute(value = PARAM_NAME_USER_EMAIL) String email,
            @RequestParam(value = PARAM_NAME_NEW_LOGIN) String login,
            @SessionAttribute(value = PARAM_NAME_ROLE) String role,
            @SessionAttribute(value = ATTR_NAME_LANGUAGE) Locale language
    ) throws CommandException {
        if (language == null){
            language = new Locale(EN_LOCALE);
        }
        ModelAndView modelAndView = new ModelAndView();
        if (!Validation.validateLogin(login)) {
            modelAndView.addObject(ATTR_NAME_ERROR_CHANGE_LOGIN,
                    MessageManager.getProperty(CHANGE_LOGIN_ERROR_MSG_KEY, language));
            modelAndView.setViewName(ConfigurationManager.getProperty(USER_PROFILE_PAGE_PATH));
        }

        try {
            if (UpdateUserLogic.updateLogin(role, login, email)) {
                modelAndView.addObject(ATTR_NAME_USER_LOGIN, login);
            } else {
                modelAndView.addObject(ATTR_NAME_ERROR_LOGIN_EXISTS,
                        MessageManager.getProperty(LOGIN_EXISTS_MSG_KEY, language));
            }
        } catch (LogicException e) {
            throw new CommandException(e);
        }
        modelAndView.setViewName(ConfigurationManager.getProperty(USER_PROFILE_PAGE_PATH));
        return modelAndView;
    }
}

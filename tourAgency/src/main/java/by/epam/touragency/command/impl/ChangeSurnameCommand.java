package by.epam.touragency.command.impl;

import by.epam.touragency.exception.CommandException;
import by.epam.touragency.exception.LogicException;
import by.epam.touragency.logic.UpdateUserLogic;
import by.epam.touragency.resource.ConfigurationManager;
import by.epam.touragency.resource.MessageManager;
import by.epam.touragency.util.Validation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.util.Locale;

import static by.epam.touragency.util.PageMsgConstant.CHANGE_SURNAME_ERROR_MSG_KEY;
import static by.epam.touragency.util.PageMsgConstant.USER_PROFILE_PAGE_PATH;
import static by.epam.touragency.util.ParameterConstant.*;

@Controller
public class ChangeSurnameCommand {
    @PostMapping("/change_user_surname")
    public ModelAndView execute(
            @SessionAttribute(value = PARAM_NAME_USER_LOGIN) String login,
            @RequestParam(value = PARAM_NAME_NEW_SURNAME) String newSurname,
            @SessionAttribute(value = PARAM_NAME_ROLE) String role,
            @SessionAttribute(value = ATTR_NAME_LANGUAGE, required = false) Locale language
    ) throws CommandException {
        if (language == null){
            language = new Locale(EN_LOCALE);
        }
        ModelAndView modelAndView = new ModelAndView();
        if (!Validation.validateName(newSurname)){
            modelAndView.addObject(ATTR_NAME_ERROR_CHANGE_USER_SURNAME,
                    MessageManager.getProperty(CHANGE_SURNAME_ERROR_MSG_KEY, language));
            modelAndView.setViewName(ConfigurationManager.getProperty(USER_PROFILE_PAGE_PATH));
            return modelAndView;
        }
        try {
            UpdateUserLogic.updateSurname(role, login, newSurname);
        } catch (LogicException e) {
            throw new CommandException(e);
        }
        modelAndView.addObject(ATTR_NAME_USER_SURNAME, newSurname);
        modelAndView.setViewName(ConfigurationManager.getProperty(USER_PROFILE_PAGE_PATH));
        return modelAndView;
    }
}

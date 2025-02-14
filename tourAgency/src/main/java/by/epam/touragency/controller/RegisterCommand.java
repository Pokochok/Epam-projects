package by.epam.touragency.controller;

import by.epam.touragency.entity.Role;
import by.epam.touragency.exception.ControllerException;
import by.epam.touragency.exception.LogicException;
import by.epam.touragency.logic.MatchOfUniqueFieldsDetector;
import by.epam.touragency.logic.UserRegistrationLogic;
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
public class RegisterCommand {

    @Autowired
    MatchOfUniqueFieldsDetector matchOfUniqueFieldsDetector;

    @Autowired
    private Validation validation;

    @Autowired
    UserRegistrationLogic userRegistrationLogic;

    @Autowired
    private MessageManager messageManager;

    @Secured("ROLE_ANONYMOUS")
    @PostMapping("/registration")
    public ModelAndView execute(
            @RequestParam(value = PARAM_NAME_NAME) String name,
            @RequestParam(value = PARAM_NAME_SURNAME) String surname,
            @RequestParam(value = PARAM_NAME_EMAIL) String email,
            @RequestParam(value = PARAM_NAME_PHONE_NUMBER) String phoneNumber,
            @RequestParam(value = PARAM_NAME_LOGIN) String login,
            @RequestParam(value = PARAM_NAME_PASSWORD) String password,
            @RequestParam(value = PARAM_NAME_USER_ROLE) String role,
            @SessionAttribute(value = ATTR_NAME_LANGUAGE, required = false) String language
    ) throws ControllerException {
        String page = null;
        boolean isValid = true;
        ModelAndView modelAndView = new ModelAndView();
        if (language == null) {
            language = EN_LOCALE;
        }

        try {
            if (!validation.validateName(name) || !validation.validateName(surname) || !validation.validateEmail(email) ||
                    !validation.validatePhoneNumber(phoneNumber) || !validation.validateLogin(login) ||
                    !validation.validatePassword(password)) {
                modelAndView.setViewName(ConfigurationManager.getProperty(REGISTRATION_PAGE_PATH));
                return modelAndView;
            }
            if (matchOfUniqueFieldsDetector.isExistsLogin(login)) {
                isValid = false;
                modelAndView.addObject(ATTR_NAME_ERROR_LOGIN_EXISTS, messageManager.getProperty(LOGIN_EXISTS_MSG_KEY, new Locale(language)));
            }
            if (matchOfUniqueFieldsDetector.isExistsEmail(email)) {
                isValid = false;
                modelAndView.addObject(ATTR_NAME_ERROR_EMAIL_EXISTS, messageManager.getProperty(EMAIL_EXISTS_MSG_KEY, new Locale(language)));
            }
            if (matchOfUniqueFieldsDetector.isExistsPhoneNumber(phoneNumber)) {
                isValid = false;
                modelAndView.addObject(ATTR_NAME_ERROR_PHONE, messageManager.getProperty(PHONE_EXISTS_MSG_KEY, new Locale(language)));
            }

            String msg = null;
            if (isValid) {
                userRegistrationLogic.addUser(name, surname, email, phoneNumber, login, password, Role.valueOf(role.toUpperCase()));
                msg = REGISTRATION_SUCCESS_MSG_KEY;
                page = "redirect:" + ConfigurationManager.getProperty(INF_URL_PATH) + "?" + ATTR_NAME_MSG_KEY + "=" + msg;
            } else {
                modelAndView.addObject(ATTR_NAME_MSG_KEY, REGISTRATION_NOT_SUCCESS_MSG_KEY);
                page = ConfigurationManager.getProperty(REGISTRATION_PAGE_PATH);
            }
        } catch (LogicException e) {
            throw new ControllerException(e);
        }
        modelAndView.setViewName(page);
        return modelAndView;
    }
}

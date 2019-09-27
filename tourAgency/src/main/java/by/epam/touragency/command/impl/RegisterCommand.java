package by.epam.touragency.command.impl;

import by.epam.touragency.command.ActionCommand;
import by.epam.touragency.controller.SessionRequestContent;
import by.epam.touragency.entity.Role;
import by.epam.touragency.exception.CommandException;
import by.epam.touragency.exception.LogicException;
import by.epam.touragency.logic.MatchOfUniqueFieldsDetector;
import by.epam.touragency.logic.UserRegistrationLogic;
import by.epam.touragency.resource.ConfigurationManager;
import by.epam.touragency.resource.MessageManager;
import by.epam.touragency.util.Validation;

import java.util.Locale;

import static by.epam.touragency.util.PageMsgConstant.*;
import static by.epam.touragency.util.ParameterConstant.*;

public class RegisterCommand implements ActionCommand {
    @Override
    public String execute(SessionRequestContent content) throws CommandException {
        String page = null;
        boolean isValid = true;
        String name = content.getParameter(PARAM_NAME_NAME);
        String surname = content.getParameter(PARAM_NAME_SURNAME);
        String email = content.getParameter(PARAM_NAME_EMAIL);
        String phoneNumber = content.getParameter(PARAM_NAME_PHONE_NUMBER);
        String login = content.getParameter(PARAM_NAME_LOGIN);
        String password = content.getParameter(PARAM_NAME_PASSWORD);
        Role role = Role.valueOf(content.getParameter(PARAM_NAME_USER_ROLE).toUpperCase());
        String language = content.getSessionAttribute(ATTR_NAME_LANGUAGE) != null ?
                content.getSessionAttribute(ATTR_NAME_LANGUAGE).toString() : content.getLocalName();

        try {
            if (!Validation.validateName(name) || !Validation.validateName(surname) || !Validation.validateEmail(email) ||
                    !Validation.validatePhoneNumber(phoneNumber) || !Validation.validateLogin(login) ||
                    !Validation.validatePassword(password)){
                return ConfigurationManager.getProperty(REGISTRATION_PAGE_PATH);
            }
                if (MatchOfUniqueFieldsDetector.isExistsLogin(login)) {
                    isValid = false;
                    content.setAttribute(ATTR_NAME_ERROR_LOGIN_EXISTS, MessageManager.getProperty(LOGIN_EXISTS_MSG_KEY, new Locale(language)));
                }
            if (MatchOfUniqueFieldsDetector.isExistsEmail(email)) {
                isValid = false;
                content.setAttribute(ATTR_NAME_ERROR_EMAIL_EXISTS, MessageManager.getProperty(EMAIL_EXISTS_MSG_KEY, new Locale(language)));
            }
            if (MatchOfUniqueFieldsDetector.isExistsPhoneNumber(phoneNumber)) {
                isValid = false;
                content.setAttribute(ATTR_NAME_ERROR_PHONE, MessageManager.getProperty(PHONE_EXISTS_MSG_KEY, new Locale(language)));
            }

            if (isValid) {
                UserRegistrationLogic.addUser(name, surname, email, phoneNumber, login, password, role);
                content.setAttribute(ATTR_NAME_MSG_KEY, REGISTRATION_SUCCESS_MSG_KEY);
                page = ConfigurationManager.getProperty(INF_PAGE_FLAG);
            } else {
                page = ConfigurationManager.getProperty(REGISTRATION_PAGE_PATH);
            }
        } catch (LogicException e) {
            throw new CommandException(e);
        }
        return page;
    }
}

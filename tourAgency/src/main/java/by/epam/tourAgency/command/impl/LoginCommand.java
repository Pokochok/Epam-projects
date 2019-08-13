package by.epam.tourAgency.command.impl;

import by.epam.tourAgency.command.ActionCommand;
import by.epam.tourAgency.controller.SessionRequestContent;
import by.epam.tourAgency.entity.User;
import by.epam.tourAgency.exception.CommandException;
import by.epam.tourAgency.exception.LogicException;
import by.epam.tourAgency.logic.LoginLogic;
import by.epam.tourAgency.resource.ConfigurationManager;
import by.epam.tourAgency.resource.MessageManager;
import by.epam.tourAgency.util.Validation;

import java.util.Locale;

import static by.epam.tourAgency.util.PageMsgConstant.*;
import static by.epam.tourAgency.util.ParameterConstant.*;

public class LoginCommand implements ActionCommand {
    @Override
    public String execute(SessionRequestContent content) throws CommandException {
        String page = null;
        String login = content.getParameter(PARAM_NAME_LOGIN);
        String password = content.getParameter(PARAM_NAME_PASSWORD);
        String language = content.getSessionAttribute(ATTR_NAME_LANGUAGE) != null ?
                content.getSessionAttribute(ATTR_NAME_LANGUAGE).toString()
                : content.getLocalName();
        boolean flag = Validation.validateLogin(login) && Validation.validatePassword(password);
        System.out.println(flag + ", "+login+", "+password);
        User user = null;
        if (flag) {
            try {
                user = LoginLogic.checkLoginPassword(login, password);
            } catch (LogicException e) {
                throw new CommandException(e);
            }
        }
        if (flag && user != null && user.getId() != 0) {
            content.setAttribute(ATTR_NAME_USER_ID, user.getId());
            content.setAttribute(ATTR_NAME_USER_NAME, user.getName());
            content.setAttribute(ATTR_NAME_USER_SURNAME, user.getSurname());
            content.setAttribute(ATTR_NAME_USER_EMAIL, user.getEmail());
            content.setAttribute(ATTR_NAME_USER_PHONE_NUMBER, user.getPhoneNumber());
            content.setAttribute(ATTR_NAME_USER_LOGIN, user.getLogin());
            content.setAttribute(ATTR_NAME_USER_STATUS, user.getStatus());
            content.setAttribute(ATTR_NAME_USER_ROLE, user.getRole().toString());
            page = ConfigurationManager.getProperty(HOME_PAGE_PATH);
        } else {
            LOGGER.debug("Incorrect login or password");
            content.setAttribute(ATTR_NAME_ERROR_LOGIN,
                    MessageManager.getProperty(LOGIN_ERROR_MSG_KEY, new Locale(language)));
            page = ConfigurationManager.getProperty(LOGIN_PAGE_PATH);
        }
        return page;
    }
}

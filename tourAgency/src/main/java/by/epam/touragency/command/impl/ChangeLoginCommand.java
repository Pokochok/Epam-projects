package by.epam.touragency.command.impl;

import by.epam.touragency.command.ActionCommand;
import by.epam.touragency.controller.SessionRequestContent;
import by.epam.touragency.exception.CommandException;
import by.epam.touragency.exception.LogicException;
import by.epam.touragency.logic.UpdateUserLogic;
import by.epam.touragency.resource.ConfigurationManager;
import by.epam.touragency.resource.MessageManager;
import by.epam.touragency.util.Validation;


import java.util.Locale;

import static by.epam.touragency.util.PageMsgConstant.*;
import static by.epam.touragency.util.ParameterConstant.*;

public class ChangeLoginCommand implements ActionCommand {
    @Override
    public String execute(SessionRequestContent content) throws CommandException {
        String email = String.valueOf(content.getSessionAttribute(PARAM_NAME_USER_EMAIL));
        String login = content.getParameter(PARAM_NAME_NEW_LOGIN);
        String role = String.valueOf(content.getSessionAttribute(PARAM_NAME_ROLE));
        String language = content.getSessionAttribute(ATTR_NAME_LANGUAGE) != null ?
                content.getSessionAttribute(ATTR_NAME_LANGUAGE).toString()
                : content.getLocalName();

        if (!Validation.validateLogin(login)) {
            content.setAttribute(ATTR_NAME_ERROR_CHANGE_LOGIN,
                    MessageManager.getProperty(CHANGE_LOGIN_ERROR_MSG_KEY, new Locale(language)));
            return ConfigurationManager.getProperty(USER_PROFILE_PAGE_PATH);
        }

        try {
            if (UpdateUserLogic.updateLogin(role, login, email)) {
                content.setAttribute(ATTR_NAME_USER_LOGIN, login);
            } else {
                content.setAttribute(ATTR_NAME_ERROR_LOGIN_EXISTS,
                        MessageManager.getProperty(LOGIN_EXISTS_MSG_KEY, new Locale(language)));
            }
        } catch (LogicException e) {
            throw new CommandException(e);
        }
        return ConfigurationManager.getProperty(USER_PROFILE_PAGE_PATH);
    }
}

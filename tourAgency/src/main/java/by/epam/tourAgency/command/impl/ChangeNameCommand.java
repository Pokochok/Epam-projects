package by.epam.tourAgency.command.impl;

import by.epam.tourAgency.command.ActionCommand;
import by.epam.tourAgency.controller.SessionRequestContent;
import by.epam.tourAgency.exception.CommandException;
import by.epam.tourAgency.exception.LogicException;
import by.epam.tourAgency.logic.UpdateUserLogic;
import by.epam.tourAgency.resource.ConfigurationManager;
import by.epam.tourAgency.resource.MessageManager;
import by.epam.tourAgency.util.Validation;

import java.util.Locale;

import static by.epam.tourAgency.util.PageMsgConstant.CHANGE_USER_NAME_ERROR_MSG_KEY;
import static by.epam.tourAgency.util.PageMsgConstant.USER_PROFILE_PAGE_PATH;
import static by.epam.tourAgency.util.ParameterConstant.*;


public class ChangeNameCommand implements ActionCommand {
    @Override
    public String execute(SessionRequestContent content) throws CommandException {
        String login = String.valueOf(content.getSessionAttribute(PARAM_NAME_USER_LOGIN));
        String newName = content.getParameter(PARAM_NAME_NEW_NAME);
        String role = String.valueOf(content.getSessionAttribute(PARAM_NAME_ROLE));
        String language = content.getSessionAttribute(ATTR_NAME_LANGUAGE) != null ?
                content.getSessionAttribute(ATTR_NAME_LANGUAGE).toString()
                : content.getLocalName();

        if (!Validation.validateName(newName)) {
            content.setAttribute(ATTR_NAME_ERROR_CHANGE_USER_NAME,
                    MessageManager.getProperty(CHANGE_USER_NAME_ERROR_MSG_KEY, new Locale(language)));
            return ConfigurationManager.getProperty(USER_PROFILE_PAGE_PATH);
        }
        try {
            UpdateUserLogic.updateName(role, login, newName);
        } catch (LogicException e) {
            throw new CommandException(e);
        }
        content.setAttribute(ATTR_NAME_USER_NAME, newName);
        return ConfigurationManager.getProperty(USER_PROFILE_PAGE_PATH);
    }
}

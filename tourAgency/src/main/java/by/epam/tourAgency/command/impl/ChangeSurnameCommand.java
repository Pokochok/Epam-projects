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

import static by.epam.tourAgency.util.PageMsgConstant.CHANGE_SURNAME_ERROR_MSG_KEY;
import static by.epam.tourAgency.util.PageMsgConstant.USER_PROFILE_PAGE_PATH;
import static by.epam.tourAgency.util.ParameterConstant.*;

public class ChangeSurnameCommand implements ActionCommand {
    @Override
    public String execute(SessionRequestContent content) throws CommandException {
        String login = String.valueOf(content.getSessionAttribute(PARAM_NAME_USER_LOGIN));
        String newSurname = content.getParameter(PARAM_NAME_NEW_SURNAME);
        String role = String.valueOf(content.getSessionAttribute(PARAM_NAME_ROLE));
        String language = content.getSessionAttribute(ATTR_NAME_LANGUAGE) != null ?
                content.getSessionAttribute(ATTR_NAME_LANGUAGE).toString()
                : content.getLocalName();

        if (!Validation.validateName(newSurname)){
            content.setAttribute(ATTR_NAME_ERROR_CHANGE_USER_SURNAME,
                    MessageManager.getProperty(CHANGE_SURNAME_ERROR_MSG_KEY, new Locale(language)));
            return ConfigurationManager.getProperty(USER_PROFILE_PAGE_PATH);
        }
        try {
            UpdateUserLogic.updateSurname(role, login, newSurname);
        } catch (LogicException e) {
            throw new CommandException(e);
        }
        content.setAttribute(ATTR_NAME_USER_SURNAME, newSurname);
        return ConfigurationManager.getProperty(USER_PROFILE_PAGE_PATH);
    }
}

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

import static by.epam.tourAgency.util.PageMsgConstant.*;
import static by.epam.tourAgency.util.ParameterConstant.*;

public class ChangePasswordCommand implements ActionCommand {
    @Override
    public String execute(SessionRequestContent content) throws CommandException {
        String login = String.valueOf(content.getSessionAttribute(PARAM_NAME_USER_LOGIN));
        String newPassword = content.getParameter(PARAM_NAME_NEW_PASSWORD);
        String password = content.getParameter(PARAM_NAME_PASSWORD);
        String role = String.valueOf(content.getSessionAttribute(PARAM_NAME_ROLE));
        String language = content.getSessionAttribute(ATTR_NAME_LANGUAGE) != null ?
                content.getSessionAttribute(ATTR_NAME_LANGUAGE).toString()
                : content.getLocalName();

        if (!Validation.validatePassword(password) || !Validation.validatePassword(newPassword)){
            content.setAttribute(ATTR_NAME_ERROR_CHANGE_PASSWORD,
                    MessageManager.getProperty(CHANGE_PASSWORD_ERROR_MSG_KEY, new Locale(language)));
            return ConfigurationManager.getProperty(USER_PROFILE_PAGE_PATH);
        }

        try {
            if (UpdateUserLogic.updatePassword(role, login, password, newPassword)) {
                content.setAttribute(ATTR_NAME_RESULT_CHANGE_PASSWORD,
                        MessageManager.getProperty(CHANGE_PASSWORD_SUCCESS_MSG_KEY, new Locale(language)));
            } else {
                content.setAttribute(ATTR_NAME_RESULT_CHANGE_PASSWORD,
                        MessageManager.getProperty(CHANGE_PASSWORD_NOT_FIND_MSG_KEY, new Locale(language)));
            }
        }catch (LogicException e){
            throw new CommandException(e);
        }
        return ConfigurationManager.getProperty(USER_PROFILE_PAGE_PATH);

    }
}

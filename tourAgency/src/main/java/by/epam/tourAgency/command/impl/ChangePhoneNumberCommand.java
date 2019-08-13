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

public class ChangePhoneNumberCommand implements ActionCommand {
    @Override
    public String execute(SessionRequestContent content) throws CommandException {
        String login = String.valueOf(content.getSessionAttribute(PARAM_NAME_USER_LOGIN));
        String newPhoneNumber = content.getParameter(PARAM_NAME_NEW_PHONE_NUMBER);
        String role = String.valueOf(content.getSessionAttribute(PARAM_NAME_ROLE));
        String language = content.getSessionAttribute(ATTR_NAME_LANGUAGE) != null ?
                content.getSessionAttribute(ATTR_NAME_LANGUAGE).toString()
                : content.getLocalName();

        if(!Validation.validatePhoneNumber(newPhoneNumber)){
            content.setAttribute(ATTR_NAME_ERROR_CHANGE_PN,
                    MessageManager.getProperty(CHANGE_PN_ERROR_MSG_KEY, new Locale(language)));
            return ConfigurationManager.getProperty(USER_PROFILE_PAGE_PATH);
        }
        try {
            if (UpdateUserLogic.updatePhoneNumber(role, newPhoneNumber, login)) {
                content.setAttribute(ATTR_NAME_USER_PHONE_NUMBER, newPhoneNumber);
            } else {
                content.setAttribute(ATTR_NAME_ERROR_PN_EXISTS,
                        MessageManager.getProperty(PHONE_NUMBER_EXISTS_MSG_KEY, new Locale(language)));
            }
        }catch (LogicException e){
            throw new CommandException(e);
        }
        return ConfigurationManager.getProperty(USER_PROFILE_PAGE_PATH);
    }
}

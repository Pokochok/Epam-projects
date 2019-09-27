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

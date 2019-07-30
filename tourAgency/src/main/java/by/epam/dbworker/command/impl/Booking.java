package by.epam.dbworker.command.impl;

import by.epam.dbworker.command.ActionCommand;
import by.epam.dbworker.controller.SessionRequestContent;
import by.epam.dbworker.entity.Order;
import by.epam.dbworker.entity.User;
import by.epam.dbworker.manager.LoginLogic;
import by.epam.dbworker.resource.ConfigurationManager;
import by.epam.dbworker.resource.MessageManager;
import by.epam.dbworker.util.SHAEncrypting;

import java.util.Locale;

public class Booking implements ActionCommand {
    @Override
    public String execute(SessionRequestContent request) {
        String page = null;
        /*String login = request.getParameter(PARAM_NAME_LOGIN);
        String password = request.getParameter(PARAM_NAME_PASSWORD);
        String encryptedPassword = SHAEncrypting.hidePassword(password);

        Order order;
        if ((order = LoginLogic.checkLogin(login, encryptedPassword))
                != null) {
            request.setAttribute(ATTR_NAME_USER_NAME, order.getName());
            request.setAttribute(ATTR_NAME_USER_SURNAME, order.getSurname());
            request.setAttribute(ATTR_NAME_USER_EMAIL, order.getEmail());
            request.setAttribute(ATTR_NAME_USER_PHONE_NUMBER, order.getPhoneNumber());
            request.setAttribute(ATTR_NAME_USER_LOGIN, order.getLogin());
            request.setAttribute(ATTR_NAME_USER_STATUS, order.getStatus());
            request.setAttribute(ATTR_NAME_USER_ROLE, order.getRole().toString()); // FIXME: 17/07/2019 не точно, что toString
            page = ConfigurationManager.getProperty(HOME_PAGE_PATH);
        } else {
            LOGGER.debug("Incorrect login or password");
            String language = request.getSessionAttribute(ATTR_NAME_LANGUAGE) != null ?
                    request.getSessionAttribute(ATTR_NAME_LANGUAGE).toString()
                    : request.getLocalName();
            request.setAttribute(ATTR_NAME_ERROR_LOGIN,
                    MessageManager.getProperty(LOGIN_ERROR_MSG_KEY, new Locale(language)));
            page = ConfigurationManager.getProperty(LOGIN_PAGE_PATH);
        }*/
        return page;
    }
}

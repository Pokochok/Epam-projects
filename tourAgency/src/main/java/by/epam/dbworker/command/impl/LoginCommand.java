package by.epam.dbworker.command.impl;

import by.epam.dbworker.command.ActionCommand;
import by.epam.dbworker.controller.SessionRequestContent;
import by.epam.dbworker.entity.User;
import by.epam.dbworker.manager.LoginLogic;
import by.epam.dbworker.resource.ConfigurationManager;
import by.epam.dbworker.resource.MessageManager;
import by.epam.dbworker.util.SHAEncrypting;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Locale;

public class LoginCommand implements ActionCommand {
    private static final Logger LOGGER = LogManager.getLogger();

    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";

    private static final String ATTR_NAME_LANGUAGE = "language";
    private static final String ATTR_NAME_USER_NAME = "userName";
    private static final String ATTR_NAME_USER_SURNAME = "userSurname";
    private static final String ATTR_NAME_USER_ROLE = "userRole";
    private static final String ATTR_NAME_USER_STATUS = "userStatus";
    private static final String ATTR_NAME_USER_LOGIN = "userLogin";
    private static final String ATTR_NAME_USER_EMAIL = "userEmail";
    private static final String ATTR_NAME_USER_PHONE_NUMBER = "userPhoneNumber";

    private static final String ATTR_NAME_ERROR_LOGIN = "errorLoginPassMessage";

    private static final String LOGIN_ERROR_MSG_KEY = "logIn.message.loginError";

    private static final String HOME_PAGE_PATH = "path.page.main";
    private static final String LOGIN_PAGE_PATH = "path.page.login";

    @Override
    public String execute(SessionRequestContent request) {
        String page = null;
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String password = request.getParameter(PARAM_NAME_PASSWORD);
        String encryptedPassword = SHAEncrypting.hidePassword(password);

        User user;
        if ((user = LoginLogic.checkLogin(login, encryptedPassword))
                != null) {
            request.setAttribute(ATTR_NAME_USER_NAME, user.getName());
            request.setAttribute(ATTR_NAME_USER_SURNAME, user.getSurname());
            request.setAttribute(ATTR_NAME_USER_EMAIL, user.getEmail());
            request.setAttribute(ATTR_NAME_USER_PHONE_NUMBER, user.getPhoneNumber());
            request.setAttribute(ATTR_NAME_USER_LOGIN, user.getLogin());
            request.setAttribute(ATTR_NAME_USER_STATUS, user.getStatus());
            request.setAttribute(ATTR_NAME_USER_ROLE, user.getRole().toString()); // FIXME: 17/07/2019 не точно, что toString
            page = ConfigurationManager.getProperty(HOME_PAGE_PATH);
        } else {
            LOGGER.debug("Incorrect login or password");
            String language = request.getSessionAttribute(ATTR_NAME_LANGUAGE) != null ?
                    request.getSessionAttribute(ATTR_NAME_LANGUAGE).toString()
                    : request.getLocalName();
            request.setAttribute(ATTR_NAME_ERROR_LOGIN,
                    MessageManager.getProperty(LOGIN_ERROR_MSG_KEY, new Locale(language)));
            page = ConfigurationManager.getProperty(LOGIN_PAGE_PATH);
        }
        return page;
    }
}

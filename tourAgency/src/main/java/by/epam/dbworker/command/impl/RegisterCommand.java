package by.epam.dbworker.command.impl;

import by.epam.dbworker.command.ActionCommand;
import by.epam.dbworker.controller.SessionRequestContent;
import by.epam.dbworker.entity.Role;
import by.epam.dbworker.entity.User;
import by.epam.dbworker.manager.MatchOfUniqueFieldsDetector;
import by.epam.dbworker.repository.Repository;
import by.epam.dbworker.repository.impl.UserRepository;
import by.epam.dbworker.resource.ConfigurationManager;
import by.epam.dbworker.resource.MessageManager;
import by.epam.dbworker.specification.impl.agentspecification.AddAgentSpecification;
import by.epam.dbworker.specification.impl.clientspecification.AddClientSpecification;
import by.epam.dbworker.specification.Specification;
import by.epam.dbworker.util.SHAEncrypting;

import java.util.Locale;

public class RegisterCommand implements ActionCommand {
    private static final String PARAM_NAME_NAME = "name";
    private static final String PARAM_NAME_SURNAME = "surname";
    private static final String PARAM_NAME_EMAIL = "email";
    private static final String PARAM_NAME_PHONE_NUMBER = "phoneNumber";
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";
    private static final String PARAM_NAME_USER_ROLE = "userRole";

    private static final String ATTR_NAME_LANGUAGE = "language";
    private static final String ATTR_NAME_USER_NAME = "userName";
    private static final String ATTR_NAME_ERROR_LOGIN = "errorLoginExistsMessage";
    private static final String ATTR_NAME_ERROR_PHONE = "errorPhoneNumberExistsMessage";
    private static final String ATTR_NAME_ERROR_EMAIL = "errorEmailExistsMessage";

    private static final String LOGIN_EXISTS_MSG_KEY = "registration.message.loginExists";
    private static final String EMAIL_EXISTS_MSG_KEY = "registration.message.emailExists";
    private static final String PHONE_EXISTS_MSG_KEY = "registration.message.phoneNumberExists";

    private static final String COMPLETION_PAGE_PATH = "path.page.main";
    private static final String REGISTRATION_PAGE_PATH = "path.page.registration";

    @Override
    public String execute(SessionRequestContent request) {
        String page = null;
        boolean isValid = true;
        String name = request.getParameter(PARAM_NAME_NAME);
        String surname = request.getParameter(PARAM_NAME_SURNAME);
        String email = request.getParameter(PARAM_NAME_EMAIL);
        String phoneNumber = request.getParameter(PARAM_NAME_PHONE_NUMBER);
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String password = request.getParameter(PARAM_NAME_PASSWORD);
        Role role = Role.valueOf(request.getParameter(PARAM_NAME_USER_ROLE).toUpperCase());
        String language = request.getSessionAttribute(ATTR_NAME_LANGUAGE) != null ?
                request.getSessionAttribute(ATTR_NAME_LANGUAGE).toString() : request.getLocalName();

        if (MatchOfUniqueFieldsDetector.isExistsLogin(login, role)) {
            isValid = false;
            request.setAttribute(ATTR_NAME_ERROR_LOGIN, MessageManager.getProperty(LOGIN_EXISTS_MSG_KEY, new Locale(language)));
        }
        if (MatchOfUniqueFieldsDetector.isExistsEmail(email, role)) {
            isValid = false;
            request.setAttribute(ATTR_NAME_ERROR_EMAIL, MessageManager.getProperty(EMAIL_EXISTS_MSG_KEY, new Locale(language)));
        }
        if (MatchOfUniqueFieldsDetector.isExistsPhoneNumber(phoneNumber, role)) {
            isValid = false;
            request.setAttribute(ATTR_NAME_ERROR_PHONE, MessageManager.getProperty(PHONE_EXISTS_MSG_KEY, new Locale(language)));
        }
        if (isValid) {
            Repository repository = UserRepository.getInstance();
            User user = new User(name, surname, email, phoneNumber, login, SHAEncrypting.hidePassword(password), role);
            Specification specification = null;
            switch (role) {
                case CLIENT: {
                    specification = new AddClientSpecification(user);
                    break;
                }
                case AGENT: {
                    specification = new AddAgentSpecification(user);
                    break;
                }
                default: {
                    return ConfigurationManager.getProperty(REGISTRATION_PAGE_PATH);
                }
            }
            repository.add(user, specification);
            request.setAttribute(ATTR_NAME_USER_NAME, name);
            page = ConfigurationManager.getProperty(COMPLETION_PAGE_PATH);
        } else {
            page = ConfigurationManager.getProperty(REGISTRATION_PAGE_PATH);
        }
        return page;
    }
}

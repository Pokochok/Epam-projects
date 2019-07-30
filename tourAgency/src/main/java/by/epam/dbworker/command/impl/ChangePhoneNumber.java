package by.epam.dbworker.command.impl;

import by.epam.dbworker.command.ActionCommand;
import by.epam.dbworker.controller.SessionRequestContent;
import by.epam.dbworker.entity.Role;
import by.epam.dbworker.repository.Repository;
import by.epam.dbworker.repository.impl.UserRepository;
import by.epam.dbworker.resource.ConfigurationManager;
import by.epam.dbworker.resource.MessageManager;
import by.epam.dbworker.specification.Specification;
import by.epam.dbworker.specification.impl.adminspecification.UpdateAdminPhoneNumberByLogin;
import by.epam.dbworker.specification.impl.agentspecification.UpdateAgentPhoneNumberByLogin;
import by.epam.dbworker.specification.impl.clientspecification.UpdateClientPhoneByLogin;

import java.util.Locale;

public class ChangePhoneNumber implements ActionCommand {
    private static final String USER_PROFILE_PAGE_PATH = "path.page.userProfile";

    private static final String ATTR_NAME_LANGUAGE = "language";
    private static final String ATTR_NAME_ERROR_CHANGE_PN = "errorChangePhoneNumber";

    private static final String CHANGE_PN_ERROR_MSG_KEY = "profile.message.errorChangePN";

    private static final String PARAM_NAME_LOGIN = "userLogin";
    private static final String PARAM_NAME_ROLE = "userRole";
    private static final String PARAM_NAME_NEW_PHONE_NUMBER = "newPhoneNumber";
    private static final String ATTR_NAME_USER_PHONE_NUMBER = "userPhoneNumber";

    @Override
    public String execute(SessionRequestContent request) {
        String login = String.valueOf(request.getSessionAttribute(PARAM_NAME_LOGIN));
        String newPhoneNumber = request.getParameter(PARAM_NAME_NEW_PHONE_NUMBER);
        String role = String.valueOf(request.getSessionAttribute(PARAM_NAME_ROLE));
        String language = request.getSessionAttribute(ATTR_NAME_LANGUAGE) != null ?
                request.getSessionAttribute(ATTR_NAME_LANGUAGE).toString()
                : request.getLocalName();

        Specification specification = null;
        switch (Role.valueOf(role)) {
            case AGENT: {
                specification = new UpdateAgentPhoneNumberByLogin(newPhoneNumber, login);
                break;
            }
            case CLIENT: {
                specification = new UpdateClientPhoneByLogin(newPhoneNumber, login);
                break;
            }
            case ADMIN: {
                specification = new UpdateAdminPhoneNumberByLogin(newPhoneNumber, login);
                break;
            }
            default: {
                request.setAttribute(ATTR_NAME_ERROR_CHANGE_PN,
                        MessageManager.getProperty(CHANGE_PN_ERROR_MSG_KEY, new Locale(language)));
                return ConfigurationManager.getProperty(USER_PROFILE_PAGE_PATH);
            }
        }

        Repository repository = UserRepository.getInstance();
        repository.update(null, specification);
        request.setAttribute(ATTR_NAME_USER_PHONE_NUMBER, newPhoneNumber);
        return ConfigurationManager.getProperty(USER_PROFILE_PAGE_PATH);
    }
}

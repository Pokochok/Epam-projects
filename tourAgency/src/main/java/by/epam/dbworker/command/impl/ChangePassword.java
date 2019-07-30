package by.epam.dbworker.command.impl;

import by.epam.dbworker.command.ActionCommand;
import by.epam.dbworker.controller.SessionRequestContent;
import by.epam.dbworker.entity.Role;
import by.epam.dbworker.repository.Repository;
import by.epam.dbworker.repository.impl.UserRepository;
import by.epam.dbworker.resource.ConfigurationManager;
import by.epam.dbworker.resource.MessageManager;
import by.epam.dbworker.specification.Specification;
import by.epam.dbworker.specification.impl.adminspecification.FindAdminByLoginPasswordSpecification;
import by.epam.dbworker.specification.impl.adminspecification.UpdateAdminPasswordByLoginPassword;
import by.epam.dbworker.specification.impl.agentspecification.FindAgentByLoginPasswordSpecification;
import by.epam.dbworker.specification.impl.agentspecification.UpdateAgentPasswordByLoginPassword;
import by.epam.dbworker.specification.impl.clientspecification.FindClientByLoginPasswordSpecification;
import by.epam.dbworker.specification.impl.clientspecification.UpdateClientPasswordByLoginPassword;
import by.epam.dbworker.util.SHAEncrypting;

import java.util.Locale;

public class ChangePassword implements ActionCommand {
    private static final String USER_PROFILE_PAGE_PATH = "path.page.userProfile";

    private static final String ATTR_NAME_LANGUAGE = "language";
    private static final String ATTR_NAME_ERROR_CHANGE_PN = "errorChangePassword";
    private static final String ATTR_NAME_RESULT_CHANGE_RASSWORD = "resultChangePassword";

    private static final String CHANGE_PASSWORD_ERROR_MSG_KEY = "profile.message.errorChangePassword";
    private static final String CHANGE_PASSWORD_SUCCESS_MSG_KEY = "profile.message.successChangePassword";
    private static final String CHANGE_PASSWORD_NOT_FIND_MSG_KEY = "profile.message.notFindPassword";

    private static final String PARAM_NAME_LOGIN = "userLogin";
    private static final String PARAM_NAME_ROLE = "userRole";
    private static final String PARAM_NAME_NEW_PASSWORD = "newPassword";
    private static final String PARAM_NAME_PASSWORD = "password";

    @Override
    public String execute(SessionRequestContent request) {
        String login = String.valueOf(request.getSessionAttribute(PARAM_NAME_LOGIN));
        String newPassword = request.getParameter(PARAM_NAME_NEW_PASSWORD);
        String password = request.getParameter(PARAM_NAME_PASSWORD);
        String encryptedPassword = SHAEncrypting.hidePassword(password);
        String encryptedNewPassword = SHAEncrypting.hidePassword(newPassword);
        String role = String.valueOf(request.getSessionAttribute(PARAM_NAME_ROLE));
        String language = request.getSessionAttribute(ATTR_NAME_LANGUAGE) != null ?
                request.getSessionAttribute(ATTR_NAME_LANGUAGE).toString()
                : request.getLocalName();

        Specification specificationForValidate = null;
        Specification specification = null;
        switch (Role.valueOf(role)) {
            case AGENT: {
                specificationForValidate = new FindAgentByLoginPasswordSpecification(login, encryptedPassword);
                specification = new UpdateAgentPasswordByLoginPassword(encryptedNewPassword, login, encryptedPassword);
                break;
            }
            case CLIENT: {
                specificationForValidate = new FindClientByLoginPasswordSpecification(login, encryptedPassword);
                specification = new UpdateClientPasswordByLoginPassword(encryptedNewPassword, login, encryptedPassword);
                break;
            }
            case ADMIN: {
                specificationForValidate = new FindAdminByLoginPasswordSpecification(login, encryptedPassword);
                specification = new UpdateAdminPasswordByLoginPassword(encryptedNewPassword, login, encryptedPassword);
                break;
            }
            default: {
                request.setAttribute(ATTR_NAME_ERROR_CHANGE_PN,
                        MessageManager.getProperty(CHANGE_PASSWORD_ERROR_MSG_KEY, new Locale(language)));
                return ConfigurationManager.getProperty(USER_PROFILE_PAGE_PATH);
            }
        }

        Repository repository = UserRepository.getInstance();
        if (repository.isExistsQuery(specificationForValidate)) {
            repository.update(null, specification);
            request.setAttribute(ATTR_NAME_RESULT_CHANGE_RASSWORD,
                    MessageManager.getProperty(CHANGE_PASSWORD_SUCCESS_MSG_KEY, new Locale(language)));
        } else {
            request.setAttribute(ATTR_NAME_RESULT_CHANGE_RASSWORD,
                    MessageManager.getProperty(CHANGE_PASSWORD_NOT_FIND_MSG_KEY, new Locale(language)));
        }
        return ConfigurationManager.getProperty(USER_PROFILE_PAGE_PATH);

    }
}

package by.epam.touragency.logic;

import by.epam.touragency.entity.User;
import by.epam.touragency.exception.LogicException;
import by.epam.touragency.exception.RepositoryException;
import by.epam.touragency.repository.impl.UserRepository;
import by.epam.touragency.specification.impl.admin.FindAdminByLoginPasswordSpecification;
import by.epam.touragency.specification.impl.agent.FindAgentByLoginPasswordSpecification;
import by.epam.touragency.specification.impl.client.FindClientByLoginPasswordSpecification;
import by.epam.touragency.specification.Specification;
import by.epam.touragency.util.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Set;

import static by.epam.touragency.util.PageMsgConstant.LOGGER;

/**
 * For business logic of login command
 */
@Service
public class LoginLogic {

    @Autowired
    private UserRepository repository;

    /**
     * Verifies entered login and password
     * @param enterLogin login to verify
     * @param password password to verify
     * @return user if verifies completed successfully, and null - if not
     * @throws LogicException if handled RepositoryException
     */
    private User checkLoginPassword(String enterLogin, String password) throws LogicException {
        String enterPass = BCrypt.hashpw(password, BCrypt.gensalt());
        User user = null;
        Specification clientSpecification = new FindClientByLoginPasswordSpecification(enterLogin, enterPass);
        Specification agentSpecification = new FindAgentByLoginPasswordSpecification(enterLogin, enterPass);
        Specification adminSpecification = new FindAdminByLoginPasswordSpecification(enterLogin, enterPass);
        try {
            Set<User> users;
            if (!(users = repository.query(clientSpecification)).isEmpty()) {
                user = users.iterator().next();
                LOGGER.debug("Client authorization detected");
            } else if (!(users = repository.query(agentSpecification)).isEmpty()) {
                user = users.iterator().next();
                LOGGER.debug("Agent authorization detected");
            } else if (!(users = repository.query(adminSpecification)).isEmpty()) {
                user = users.iterator().next();
                LOGGER.debug("Admin authorization detected");
            }
        } catch (RepositoryException e) {
            throw new LogicException(e);
        }
        return user;
    }

    public User checkedUser(String login, String password) throws LogicException {
        boolean flag = Validation.validateLogin(login) && Validation.validatePassword(password);
        User user = null;
        if (flag) {
            try {
                user = checkLoginPassword(login, password);
            } catch (LogicException e) {
                throw new LogicException(e);
            }
        }
        return user;
    }
}

package by.epam.tourAgency.logic;

import by.epam.tourAgency.entity.User;
import by.epam.tourAgency.exception.LogicException;
import by.epam.tourAgency.exception.RepositoryException;
import by.epam.tourAgency.repository.impl.UserRepository;
import by.epam.tourAgency.specification.impl.admin.FindAdminByLoginPasswordSpecification;
import by.epam.tourAgency.specification.impl.agent.FindAgentByLoginPasswordSpecification;
import by.epam.tourAgency.specification.impl.client.FindClientByLoginPasswordSpecification;
import by.epam.tourAgency.specification.Specification;
import by.epam.tourAgency.util.SHAEncrypting;

import java.util.Set;

import static by.epam.tourAgency.util.PageMsgConstant.LOGGER;

/**
 * For business logic of login command
 */
public class LoginLogic {

    /**
     * Verifies entered login and password
     * @param enterLogin login to verify
     * @param password password to verify
     * @return user if verifies completed successfully, and null - if not
     * @throws LogicException if handled RepositoryException
     */
    public static User checkLoginPassword(String enterLogin, String password) throws LogicException {
        String enterPass = SHAEncrypting.hidePassword(password);
        User user = null;
        UserRepository repository = UserRepository.getInstance();
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
}

package by.epam.tourAgency.logic;

import by.epam.tourAgency.entity.User;
import by.epam.tourAgency.exception.LogicException;
import by.epam.tourAgency.exception.RepositoryException;
import by.epam.tourAgency.repository.impl.UserRepository;
import by.epam.tourAgency.specification.impl.admin.FindAdminByLoginPasswordSpecification;
import by.epam.tourAgency.specification.impl.agent.FindAgentByLoginPasswordSpecification;
import by.epam.tourAgency.specification.impl.agent.FindAgentByLoginSpecification;
import by.epam.tourAgency.specification.impl.client.FindClientByLoginPasswordSpecification;
import by.epam.tourAgency.specification.Specification;
import by.epam.tourAgency.specification.impl.client.FindClientByLoginSpecification;
import by.epam.tourAgency.util.SHAEncrypting;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Set;

public class LoginLogic {
    private static final Logger LOGGER = LogManager.getLogger();

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

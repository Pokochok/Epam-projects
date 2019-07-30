package by.epam.dbworker.manager;

import by.epam.dbworker.entity.User;
import by.epam.dbworker.repository.impl.UserRepository;
import by.epam.dbworker.specification.impl.adminspecification.FindAdminByLoginPasswordSpecification;
import by.epam.dbworker.specification.impl.agentspecification.FindAgentByLoginPasswordSpecification;
import by.epam.dbworker.specification.impl.clientspecification.FindClientByLoginPasswordSpecification;
import by.epam.dbworker.specification.Specification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Set;

public class LoginLogic {
    private static final Logger LOGGER = LogManager.getLogger();

    public static User checkLogin(String enterLogin, String enterPass) {
        User user = null;
        UserRepository repository = UserRepository.getInstance();
        Specification clientSpecification = new FindClientByLoginPasswordSpecification(enterLogin, enterPass);
        Specification agentSpecification = new FindAgentByLoginPasswordSpecification(enterLogin, enterPass);
        Specification adminSpecification = new FindAdminByLoginPasswordSpecification(enterLogin, enterPass);

        Set<User> users;
        if (!(users = repository.query(clientSpecification)).isEmpty()){
            user = users.iterator().next();
            LOGGER.debug("Client authorization detected");
        }else if (!(users = repository.query(agentSpecification)).isEmpty()){
            user = users.iterator().next();
            LOGGER.debug("Agent authorization detected");
        }else if (!(users = repository.query(adminSpecification)).isEmpty()){
            user = users.iterator().next();
            LOGGER.debug("Admin authorization detected");
        }
        return user;
    }
}

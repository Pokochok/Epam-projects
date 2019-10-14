package by.epam.touragency.logic;

import by.epam.touragency.entity.Role;
import by.epam.touragency.entity.User;
import by.epam.touragency.exception.LogicException;
import by.epam.touragency.exception.RepositoryException;
import by.epam.touragency.repository.Repository;
import by.epam.touragency.repository.impl.UserRepository;
import by.epam.touragency.specification.Specification;
import by.epam.touragency.specification.impl.agent.AddAgentSpecification;
import by.epam.touragency.specification.impl.client.AddClientSpecification;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import static by.epam.touragency.util.PageMsgConstant.LOGGER;

/**
 * For user registration logic
 */
@Service
public class UserRegistrationLogic {
    /**
     * Adds new user to database
     * @param name user name
     * @param surname user surname
     * @param email user email
     * @param phoneNumber user phone number
     * @param login user login
     * @param password user password
     * @param role user rolee
     * @throws LogicException if handled RepositoryException
     */
    public void addUser(String name, String surname, String email, String phoneNumber, String login,
                               String password, Role role) throws LogicException {
        Repository repository = UserRepository.getInstance();
        User user = new User.UserBuilder()
                .setName(name)
                .setSurname(surname)
                .setEmail(email)
                .setPhoneNumber(phoneNumber)
                .setLogin(login)
                .setPassword(BCrypt.hashpw(password, BCrypt.gensalt()))
                .setRole(role).build();
        Specification specification = defineAddUserSpecification(role, user);
        try {
            repository.add(user, specification);
        } catch (RepositoryException e) {
            throw new LogicException(e);
        }
    }

    /**
     * Defines for what role specification needed
     * @param role user role
     * @param user user instance
     * @return specification
     * @throws LogicException if user role not defined
     */
    private Specification defineAddUserSpecification(Role role, User user) throws LogicException {
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
                LOGGER.error("User role is not defined at user registration");
                throw new LogicException("Registered user role is not defined");
            }
        }
        return specification;
    }
}

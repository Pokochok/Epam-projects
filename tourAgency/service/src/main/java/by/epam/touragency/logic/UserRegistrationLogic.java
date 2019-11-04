package by.epam.touragency.logic;

import by.epam.touragency.entity.Role;
import by.epam.touragency.entity.User;
import by.epam.touragency.repository.Repository;
import by.epam.touragency.repository.impl.UserRepository;
import by.epam.touragency.specification.Specification;
import by.epam.touragency.specification.impl.agent.AddAgentSpecification;
import by.epam.touragency.specification.impl.client.AddClientSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;


/**
 * For user registration logic
 */
@Service
public class UserRegistrationLogic {
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * Adds new user to database
     *
     * @param name        user name
     * @param surname     user surname
     * @param email       user email
     * @param phoneNumber user phone number
     * @param login       user login
     * @param password    user password
     * @param role        user rolee
     */
    public void addUser(String name, String surname, String email, String phoneNumber, String login,
                        String password, Role role) {
        Repository repository = UserRepository.getInstance();
        User user = new User.UserBuilder()
                .setName(name)
                .setSurname(surname)
                .setEmail(email)
                .setPhoneNumber(phoneNumber)
                .setLogin(login)
                .setPassword(BCrypt.hashpw(password, BCrypt.gensalt(12)))
                .setRole(role).build();
        Specification specification = defineAddUserSpecification(role, user);
        repository.add(user, specification);
    }

    /**
     * Defines for what role specification needed
     *
     * @param role user role
     * @param user user instance
     * @return specification
     */
    private Specification defineAddUserSpecification(Role role, User user) {
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
                throw new RuntimeException("Registered user role is not defined");
            }
        }
        return specification;
    }
}

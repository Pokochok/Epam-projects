package by.epam.tourAgency.logic;

import by.epam.tourAgency.entity.Role;
import by.epam.tourAgency.entity.User;
import by.epam.tourAgency.exception.LogicException;
import by.epam.tourAgency.exception.RepositoryException;
import by.epam.tourAgency.repository.Repository;
import by.epam.tourAgency.repository.impl.UserRepository;
import by.epam.tourAgency.specification.Specification;
import by.epam.tourAgency.specification.impl.agent.AddAgentSpecification;
import by.epam.tourAgency.specification.impl.client.AddClientSpecification;
import by.epam.tourAgency.util.SHAEncrypting;

import static by.epam.tourAgency.util.PageMsgConstant.LOGGER;

/**
 * For user registration logic
 */
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
    public static void addUser(String name, String surname, String email, String phoneNumber, String login,
                               String password, Role role) throws LogicException {
        Repository repository = UserRepository.getInstance();
        User user = new User.UserBuilder()
                .setName(name)
                .setSurname(surname)
                .setEmail(email)
                .setPhoneNumber(phoneNumber)
                .setLogin(login)
                .setPassword(SHAEncrypting.hidePassword(password))
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
    private static Specification defineAddUserSpecification(Role role, User user) throws LogicException {
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

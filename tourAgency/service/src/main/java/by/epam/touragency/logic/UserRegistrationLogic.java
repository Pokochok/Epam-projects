package by.epam.touragency.logic;

import by.epam.touragency.entity.Role;
import by.epam.touragency.entity.User;
import by.epam.touragency.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;


/**
 * For user registration logic
 */
@Service
public class UserRegistrationLogic {
    @Autowired
    @Qualifier("userRepository") // FIXME: 11/5/2019
    private Repository<User> userRepository;

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
        User user = new User.UserBuilder()
                .setName(name)
                .setSurname(surname)
                .setEmail(email)
                .setPhoneNumber(phoneNumber)
                .setLogin(login)
                .setPassword(BCrypt.hashpw(password, BCrypt.gensalt(12)))
                .setRole(role)
                .setStatus("ACTIVE").build();
        userRepository.add(user, null);
    }
}

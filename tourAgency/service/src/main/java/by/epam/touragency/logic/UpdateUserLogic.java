package by.epam.touragency.logic;

import by.epam.touragency.entity.User;
import by.epam.touragency.entity.UserPrincipal;
import by.epam.touragency.repository.Repository;
import by.epam.touragency.specification.Specification;
import by.epam.touragency.specification.impl.user.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


/**
 * For user updates logic
 */
@Service
public class UpdateUserLogic {
    @Autowired
    @Qualifier("hibernateUserRepository")
    private Repository<User> userRepository;

    @Autowired
    private BCryptPasswordEncoder bCrypt;

    @Autowired
    private MatchOfUniqueFieldsDetector matchOfUniqueFieldsDetector;

    /**
     * Updates user email
     *
     * @param user  user
     * @param email user email
     * @param login user login
     * @return true, if updating completed successfully, and false - if not
     */
    public boolean updateEmail(User user, String email, String login) {
        boolean flag = !(matchOfUniqueFieldsDetector.isExistsEmail(email)
                || !matchOfUniqueFieldsDetector.isExistsLogin(login));
        if (flag) {
            Specification specification = new UpdateUserEmailByLoginSpecification(email, login);
            user.setEmail(email);
            userRepository.update(user, specification);
        }
        return flag;
    }

    /**
     * Updates phone number
     *
     * @param user           user
     * @param newPhoneNumber new phone number
     * @param login          login
     * @return true, if updating completed successfully, and false - if not
     */
    public boolean updatePhoneNumber(User user, String newPhoneNumber, String login) {
        boolean flag = !(matchOfUniqueFieldsDetector.isExistsPhoneNumber(newPhoneNumber)
                || !matchOfUniqueFieldsDetector.isExistsLogin(login));
        if (flag) {
            Specification specification = new UpdateUserPhoneNumberByLoginSpecification(newPhoneNumber, login);
            user.setPhoneNumber(newPhoneNumber);
            userRepository.update(user, specification);
        }
        return flag;
    }

    /**
     * Updates user login
     *
     * @param user  user
     * @param login login
     * @param email email
     * @return true, if updating completed successfully, and false - if not
     */
    public boolean updateLogin(User user, String login, String email) {
        boolean flag = !(matchOfUniqueFieldsDetector.isExistsLogin(login)
                || !matchOfUniqueFieldsDetector.isExistsEmail(email));
        if (flag) {
            Specification specification = new UpdateUserLoginByEmailSpecification(login, email);
            user.setLogin(login);
            userRepository.update(user, specification);
        }
        return flag;
    }

    /**
     * Updates user password
     *
     * @param user        user
     * @param login       login
     * @param password    password
     * @param newPassword new password
     * @return true, if updating completed successfully, and false - if not
     */
    public boolean updatePassword(User user, String login, String password, String newPassword) {
        boolean flag = BCrypt.checkpw(password, user.getPassword());
        if (flag) {
            String encryptedNewPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt(12));
            Specification specification = new UpdateUserPasswordByLoginSpecification(login, encryptedNewPassword);
            user.setPassword(encryptedNewPassword);
            userRepository.update(user, specification);
        }
        return flag;
    }

    /**
     * Updates user name
     *
     * @param user    user
     * @param login   login
     * @param newName new name
     * @return true, if updating completed successfully, and false - if not
     */
    public boolean updateName(User user, String login, String newName) {
        Specification specification = new UpdateUserNameByLoginSpecification(newName, login);

        boolean flag = matchOfUniqueFieldsDetector.isExistsLogin(login);
        if (flag) {
            userRepository.update(user, specification);
        }
        return flag;
    }

    /**
     * Updates user surname
     *
     * @param user       user
     * @param login      login
     * @param newSurname new surname
     * @return true, if updating completed successfully, and false - if not
     */
    public boolean updateSurname(User user, String login, String newSurname) {
        Specification specification = new UpdateUserSurnameByLoginSpecification(newSurname, login);

        boolean flag = matchOfUniqueFieldsDetector.isExistsLogin(login);
        if (flag) {
            userRepository.update(user, specification);
        }
        return flag;
    }

    public boolean checkPrincipal() {
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof UserPrincipal;
    }

    public UserPrincipal getUserPrincipal() {
        return (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
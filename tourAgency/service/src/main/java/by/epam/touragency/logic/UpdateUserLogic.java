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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


/**
 * For user updates logic
 */
@Service
public class UpdateUserLogic {
    @Autowired
    @Qualifier("hibernateUserRepository")
    Repository<User> userRepository;

    @Autowired
    private BCryptPasswordEncoder bCrypt;

    @Autowired
    private MatchOfUniqueFieldsDetector matchOfUniqueFieldsDetector;

    /**
     * Updates user email
     *
     * @param role  user role
     * @param email user email
     * @param login user login
     * @return true, if updating completed successfully, and false - if not
     */
    public boolean updateEmail(String role, String email, String login) {
        boolean flag = false;
        Specification specification = new UpdateUserEmailByLoginSpecification(email, login);

        if (flag = !(matchOfUniqueFieldsDetector.isExistsEmail(email)
                || !matchOfUniqueFieldsDetector.isExistsLogin(login))) {
            userRepository.update(null, specification);
        }
        return flag;
    }

    /**
     * Updates phone number
     *
     * @param role           role
     * @param newPhoneNumber new phone number
     * @param login          login
     * @return true, if updating completed successfully, and false - if not
     */
    public boolean updatePhoneNumber(String role, String newPhoneNumber, String login) {
        boolean flag = false;
        Specification specification = new UpdateUserPhoneNumberByLoginSpecification(newPhoneNumber, login);

        if (flag = !(matchOfUniqueFieldsDetector.isExistsPhoneNumber(newPhoneNumber)
                || !matchOfUniqueFieldsDetector.isExistsLogin(login))) {
            userRepository.update(null, specification);
        }
        return flag;
    }

    /**
     * Updates user login
     *
     * @param role  role
     * @param login login
     * @param email email
     * @return true, if updating completed successfully, and false - if not
     */
    public boolean updateLogin(String role, String login, String email) {
        boolean flag = false;
        Specification specification = new UpdateUserLoginByEmailSpecification(login, email);

        if (flag = !(matchOfUniqueFieldsDetector.isExistsLogin(login)
                || !matchOfUniqueFieldsDetector.isExistsEmail(email))) {
            userRepository.update(null, specification);
        }
        return flag;
    }

    /**
     * Updates user password
     *
     * @param role        role
     * @param login       login
     * @param password    password
     * @param newPassword new password
     * @return true, if updating completed successfully, and false - if not
     */
    public boolean updatePassword(String role, String login, String password, String newPassword) {
        String encryptedNewPassword = bCrypt.encode(newPassword);
        boolean flag = false;
        Specification specificationForValidate = null;
        Specification specification = null;
        User user = null;
        specificationForValidate = new FindUserByLoginSpecification(login);
        specification = new UpdateUserPasswordByLoginSpecification(login, encryptedNewPassword);
        user = userRepository.query(specificationForValidate).iterator().next();
        if (flag = bCrypt.matches(password, user.getPassword())) {
            userRepository.update(null, specification);
        }
        return flag;
    }

    /**
     * Updates user name
     *
     * @param role    role
     * @param login   login
     * @param newName new name
     * @return true, if updating completed successfully, and false - if not
     */
    public boolean updateName(String role, String login, String newName) {
        Specification specification = new UpdateUserNameByLoginSpecification(newName, login);

        boolean flag = false;
        if (flag = matchOfUniqueFieldsDetector.isExistsLogin(login)) {
            userRepository.update(null, specification);
        }
        return flag;
    }

    /**
     * Updates user surname
     *
     * @param role       role
     * @param login      login
     * @param newSurname new surname
     * @return true, if updating completed successfully, and false - if not
     */
    public boolean updateSurname(String role, String login, String newSurname) {
        Specification specification = new UpdateUserSurnameByLoginSpecification(newSurname, login);

        boolean flag = false;
        if (flag = matchOfUniqueFieldsDetector.isExistsLogin(login)) {
            userRepository.update(null, specification);
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
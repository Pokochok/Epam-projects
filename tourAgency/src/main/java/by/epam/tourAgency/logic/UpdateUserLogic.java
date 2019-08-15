package by.epam.tourAgency.logic;

import by.epam.tourAgency.entity.Role;
import by.epam.tourAgency.exception.LogicException;
import by.epam.tourAgency.exception.RepositoryException;
import by.epam.tourAgency.repository.impl.UserRepository;
import by.epam.tourAgency.specification.Specification;
import by.epam.tourAgency.specification.impl.admin.*;
import by.epam.tourAgency.specification.impl.agent.*;
import by.epam.tourAgency.specification.impl.client.*;
import by.epam.tourAgency.util.SHAEncrypting;

import static by.epam.tourAgency.util.PageMsgConstant.LOGGER;

/**
 * For user updates logic
 */
public class UpdateUserLogic {
    /**
     * Updates user email
     * @param role user role
     * @param email user email
     * @param login user login
     * @return true, if updating completed successfully, and false - if not
     * @throws LogicException if handled RepositoryException
     */
    public static boolean updateEmail(String role, String email, String login) throws LogicException {
        boolean flag = false;
        Specification specification = defineSpecificationForEmail(role, email, login);

        UserRepository repository = UserRepository.getInstance();
        try {
            if (flag = !(MatchOfUniqueFieldsDetector.isExistsEmail(email)
                    || !MatchOfUniqueFieldsDetector.isExistsLogin(login))) {
                repository.update(null, specification);
            }
        } catch (RepositoryException e) {
            throw new LogicException(e);
        }
        return flag;
    }

    /**
     * Defines specification for email updating
     * @param role role
     * @param email email
     * @param login login
     * @return specification
     * @throws LogicException if if user role is not defined
     */
    private static Specification defineSpecificationForEmail(String role, String email, String login) throws LogicException {
        Specification specification = null;
        switch (Role.valueOf(role)) {
            case AGENT: {
                specification = new UpdateAgentEmailByLoginSpecification(email, login);
                break;
            }
            case CLIENT: {
                specification = new UpdateClientEmailByLoginSpecification(email, login);
                break;
            }
            case ADMIN: {
                specification = new UpdateAdminEmailByLoginSpecification(email, login);
                break;
            }
            default: {
                LOGGER.error("User role is not defined while updating email");
                throw new LogicException("User role is not defined");
            }
        }
        return specification;
    }

    /**
     * Updates phone number
     * @param role role
     * @param newPhoneNumber new phone number
     * @param login login
     * @return true, if updating completed successfully, and false - if not
     * @throws LogicException if handled RepositoryException
     */
    public static boolean updatePhoneNumber(String role, String newPhoneNumber, String login) throws LogicException {
        boolean flag = false;
        Specification specification = defineSpecificationForPhoneNumber(role, newPhoneNumber, login);

        UserRepository repository = UserRepository.getInstance();
        try {
            if (flag = !(MatchOfUniqueFieldsDetector.isExistsPhoneNumber(newPhoneNumber)
                    || !MatchOfUniqueFieldsDetector.isExistsLogin(login))) {
                repository.update(null, specification);
            }
        } catch (RepositoryException e) {
            throw new LogicException(e);
        }
        return flag;
    }

    /**
     * Defines specification for phone number updating
     * @param role role
     * @param newPhoneNumber new phone number
     * @param login login
     * @return specification
     * @throws LogicException if if user role is not defined
     */
    private static Specification defineSpecificationForPhoneNumber(String role, String newPhoneNumber, String login) throws LogicException {
        Specification specification = null;
        switch (Role.valueOf(role)) {
            case AGENT: {
                specification = new UpdateAgentPhoneNumberByLoginSpecification(newPhoneNumber, login);
                break;
            }
            case CLIENT: {
                specification = new UpdateClientPhoneByLoginSpecification(newPhoneNumber, login);
                break;
            }
            case ADMIN: {
                specification = new UpdateAdminPhoneNumberByLoginSpecification(newPhoneNumber, login);
                break;
            }
            default: {
                LOGGER.error("User role is not defined while updating phone number");
                throw new LogicException("User role is not defined");
            }
        }
        return specification;
    }

    /**
     * Updates user login
     * @param role role
     * @param login login
     * @param email email
     * @return true, if updating completed successfully, and false - if not
     * @throws LogicException if handled RepositoryException
     */
    public static boolean updateLogin(String role, String login, String email) throws LogicException {
        boolean flag = false;
        Specification specification = defineSpecificationForLogin(role, login, email);

        UserRepository repository = UserRepository.getInstance();
        try {
            if (flag = !(MatchOfUniqueFieldsDetector.isExistsLogin(login)
                    || !MatchOfUniqueFieldsDetector.isExistsEmail(email))) {
                repository.update(null, specification);
            }
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * Defines specification for login updating
     * @param role role
     * @param login login
     * @param email email
     * @return specification
     * @throws LogicException if if user role is not defined
     */
    private static Specification defineSpecificationForLogin(String role, String login, String email) throws LogicException {
        Specification specification = null;
        switch (Role.valueOf(role)) {
            case AGENT: {
                specification = new UpdateAgentLoginByEmailSpecification(login, email);
                break;
            }
            case CLIENT: {
                specification = new UpdateClientLoginByEmailSpecification(login, email);
                break;
            }
            case ADMIN: {
                specification = new UpdateAdminLoginByEmailSpecification(login, email);
                break;
            }
            default: {
                LOGGER.error("User role is not defined while updating login");
                throw new LogicException("User role is not defined");
            }
        }
        return specification;
    }

    /**
     * Updates user password
     * @param role role
     * @param login login
     * @param password password
     * @param newPassword new password
     * @return true, if updating completed successfully, and false - if not
     * @throws LogicException if handled RepositoryException or if user role is not defined
     */
    public static boolean updatePassword(String role, String login, String password, String newPassword) throws LogicException {
        String encryptedPassword = SHAEncrypting.hidePassword(password);
        String encryptedNewPassword = SHAEncrypting.hidePassword(newPassword);
        boolean flag = false;
        Specification specificationForValidate = null;
        Specification specification = null;
        switch (Role.valueOf(role)) {
            case AGENT: {
                specificationForValidate = new FindAgentByLoginPasswordSpecification(login, encryptedPassword);
                specification = new UpdateAgentPasswordByLoginPasswordSpecification(encryptedNewPassword, login, encryptedPassword);
                break;
            }
            case CLIENT: {
                specificationForValidate = new FindClientByLoginPasswordSpecification(login, encryptedPassword);
                specification = new UpdateClientPasswordByLoginPasswordSpecification(encryptedNewPassword, login, encryptedPassword);
                break;
            }
            case ADMIN: {
                specificationForValidate = new FindAdminByLoginPasswordSpecification(login, encryptedPassword);
                specification = new UpdateAdminPasswordByLoginPasswordSpecification(encryptedNewPassword, login, encryptedPassword);
                break;
            }
            default: {
                LOGGER.error("User role is not defined while updating password");
                throw new LogicException("User role is not defined");
            }
        }

        UserRepository repository = UserRepository.getInstance();
        try {
            if (flag = repository.isExistsQuery(specificationForValidate)) {
                repository.update(null, specification);
            }
        } catch (RepositoryException e) {
            throw new LogicException(e);
        }
        return flag;
    }

    /**
     * Updates user name
     * @param role role
     * @param login login
     * @param newName new name
     * @return true, if updating completed successfully, and false - if not
     * @throws LogicException if handled RepositoryException
     */
    public static boolean updateName(String role, String login, String newName) throws LogicException {
        Specification specification = defineSpecificationForName(role, newName, login);

        boolean flag = false;
        UserRepository repository = UserRepository.getInstance();
        try {
            if (flag = MatchOfUniqueFieldsDetector.isExistsLogin(login)) {
                repository.update(null, specification);
            }
        } catch (RepositoryException e) {
            throw new LogicException(e);
        }
        return flag;
    }

    /**
     * Defines specification for usr name updating
     * @param role role
     * @param newName new name
     * @param login login
     * @return specification
     * @throws LogicException if if user role is not defined
     */
    private static Specification defineSpecificationForName(String role, String newName, String login) throws LogicException {
        Specification specification = null;
        switch (Role.valueOf(role)) {
            case AGENT: {
                specification = new UpdateAgentNameByLoginSpecification(newName, login);
                break;
            }
            case CLIENT: {
                specification = new UpdateClientNameByLoginSpecification(newName, login);
                break;
            }
            case ADMIN: {
                specification = new UpdateAdminNameByLoginSpecification(newName, login);
                break;
            }
            default: {
                LOGGER.error("User role is not defined while updating name");
                throw new LogicException("User role is not defined");
            }
        }
        return specification;
    }

    /**
     * Updates user surname
     * @param role role
     * @param login login
     * @param newSurname new surname
     * @return true, if updating completed successfully, and false - if not
     * @throws LogicException if handled RepositoryException
     */
    public static boolean updateSurname(String role, String login, String newSurname) throws LogicException {
        Specification specification = defineSpecificationForSurname(role, newSurname, login);

        boolean flag = false;
        UserRepository repository = UserRepository.getInstance();
        try {
            if (flag = MatchOfUniqueFieldsDetector.isExistsLogin(login)) {
                repository.update(null, specification);
            }
        } catch (RepositoryException e) {
            throw new LogicException(e);
        }
        return flag;
    }

    /**
     * Defines specification for user surname Updating
     * @param role role
     * @param newSurname new surname
     * @param login login
     * @return specification
     * @throws LogicException if user role is not defined
     */
    private static Specification defineSpecificationForSurname(String role, String newSurname, String login) throws LogicException {
        Specification specification = null;
        switch (Role.valueOf(role)) {
            case AGENT: {
                specification = new UpdateAgentSurnameByLoginSpecification(newSurname, login);
                break;
            }
            case CLIENT: {
                specification = new UpdateClientSurnameByLoginSpecification(newSurname, login);
                break;
            }
            case ADMIN: {
                specification = new UpdateAdminSurnameByLoginSpecification(newSurname, login);
                break;
            }
            default: {
                LOGGER.error("User role is not defined while updating surname");
                throw new LogicException("User role is not defined");
            }
        }
        return specification;
    }
}
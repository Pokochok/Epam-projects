package by.epam.touragency.logic;

import by.epam.touragency.exception.LogicException;
import by.epam.touragency.exception.RepositoryException;
import by.epam.touragency.repository.Repository;
import by.epam.touragency.repository.impl.TourRepository;
import by.epam.touragency.repository.impl.UserRepository;
import by.epam.touragency.specification.*;
import by.epam.touragency.specification.impl.admin.FindAdminByEmailSpecification;
import by.epam.touragency.specification.impl.admin.FindAdminByLoginSpecification;
import by.epam.touragency.specification.impl.admin.FindAdminByPhoneNumberSpecification;
import by.epam.touragency.specification.impl.agent.FindAgentByEmailSpecification;
import by.epam.touragency.specification.impl.agent.FindAgentByLoginSpecification;
import by.epam.touragency.specification.impl.agent.FindAgentByPhoneNumberSpecification;
import by.epam.touragency.specification.impl.client.FindClientByEmailSpecification;
import by.epam.touragency.specification.impl.client.FindClientByLoginSpecification;
import by.epam.touragency.specification.impl.client.FindClientByPhoneNumberSpecification;
import by.epam.touragency.specification.impl.tour.FindTourByNameSpecification;

/**
 * For matching unique fields
 */
public class MatchOfUniqueFieldsDetector {
    /**
     * Checks, if such email exists
     * @param email to check
     * @return true, if email exists, and false - if not
     * @throws LogicException if handled RepositoryException
     */
    public static boolean isExistsEmail(String email) throws LogicException {
        boolean flag = false;
        Repository repository = UserRepository.getInstance();
        Specification clientSpecification = new FindClientByEmailSpecification(email);
        Specification agentSpecification = new FindAgentByEmailSpecification(email);
        Specification adminSpecification = new FindAdminByEmailSpecification(email);
        try {
            flag = repository.isExistsQuery(clientSpecification)
                    || repository.isExistsQuery(agentSpecification)
                    || repository.isExistsQuery(adminSpecification);
        } catch (RepositoryException e) {
            throw new LogicException(e);
        }
        return flag;
    }

    /**
     * Checks, if such phone number exists
     * @param phoneNumber to check
     * @return true, if phone number exists, and false - if not
     * @throws LogicException if handled RepositoryException
     */
    public static boolean isExistsPhoneNumber(String phoneNumber) throws LogicException {
        boolean flag = false;
        Repository repository = UserRepository.getInstance();
        Specification clientSpecification = new FindClientByPhoneNumberSpecification(phoneNumber);
        Specification agentSpecification = new FindAgentByPhoneNumberSpecification(phoneNumber);
        Specification adminSpecification = new FindAdminByPhoneNumberSpecification(phoneNumber);
        try {
            flag = repository.isExistsQuery(clientSpecification)
                    || repository.isExistsQuery(agentSpecification)
                    || repository.isExistsQuery(adminSpecification);
        } catch (RepositoryException e) {
            throw new LogicException(e);
        }
        return flag;
    }

    /**
     * Checks, if such login exists
     * @param login to check
     * @return true, if phone number exists, and false - if not
     * @throws LogicException if handled RepositoryException
     */
    public static boolean isExistsLogin(String login) throws LogicException {
        boolean flag = false;
        Repository repository = UserRepository.getInstance();
        Specification clientSpecification = new FindClientByLoginSpecification(login);
        Specification agentSpecification = new FindAgentByLoginSpecification(login);
        Specification adminSpecification = new FindAdminByLoginSpecification(login);
        try {
            flag = repository.isExistsQuery(clientSpecification)
                    || repository.isExistsQuery(agentSpecification)
                    || repository.isExistsQuery(adminSpecification);
        } catch (RepositoryException e) {
            throw new LogicException(e);
        }
        return flag;
    }

    /**
     * Checks, if such tour name exists
     * @param tourName to check
     * @return true, if phone number exists, and false - if not
     * @throws LogicException if handled RepositoryException
     */
    public static boolean isExistsTourName(String tourName) throws LogicException {
        Specification specification = new FindTourByNameSpecification(tourName);
        try {
            return TourRepository.getInstance().isExistsQuery(specification);
        } catch (RepositoryException e) {
            throw new LogicException(e);
        }
    }
}

package by.epam.touragency.logic;

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
import org.springframework.stereotype.Service;

/**
 * For matching unique fields
 */
@Service
public class MatchOfUniqueFieldsDetector {
    /**
     * Checks, if such email exists
     *
     * @param email to check
     * @return true, if email exists, and false - if not
     */
    public boolean isExistsEmail(String email) {
        boolean flag = false;
        Repository repository = UserRepository.getInstance();
        Specification clientSpecification = new FindClientByEmailSpecification(email);
        Specification agentSpecification = new FindAgentByEmailSpecification(email);
        Specification adminSpecification = new FindAdminByEmailSpecification(email);
        flag = repository.isExistsQuery(clientSpecification)
                || repository.isExistsQuery(agentSpecification)
                || repository.isExistsQuery(adminSpecification);
        return flag;
    }

    /**
     * Checks, if such phone number exists
     *
     * @param phoneNumber to check
     * @return true, if phone number exists, and false - if not
     */
    public boolean isExistsPhoneNumber(String phoneNumber) {
        boolean flag = false;
        Repository repository = UserRepository.getInstance();
        Specification clientSpecification = new FindClientByPhoneNumberSpecification(phoneNumber);
        Specification agentSpecification = new FindAgentByPhoneNumberSpecification(phoneNumber);
        Specification adminSpecification = new FindAdminByPhoneNumberSpecification(phoneNumber);
        flag = repository.isExistsQuery(clientSpecification)
                || repository.isExistsQuery(agentSpecification)
                || repository.isExistsQuery(adminSpecification);
        return flag;
    }

    /**
     * Checks, if such login exists
     *
     * @param login to check
     * @return true, if phone number exists, and false - if not
     */
    public boolean isExistsLogin(String login) {
        boolean flag = false;
        Repository repository = UserRepository.getInstance();
        Specification clientSpecification = new FindClientByLoginSpecification(login);
        Specification agentSpecification = new FindAgentByLoginSpecification(login);
        Specification adminSpecification = new FindAdminByLoginSpecification(login);
        flag = repository.isExistsQuery(clientSpecification)
                || repository.isExistsQuery(agentSpecification)
                || repository.isExistsQuery(adminSpecification);
        return flag;
    }

    /**
     * Checks, if such tour name exists
     *
     * @param tourName to check
     * @return true, if phone number exists, and false - if not
     */
    public boolean isExistsTourName(String tourName) {
        Specification specification = new FindTourByNameSpecification(tourName);
        return TourRepository.getInstance().isExistsQuery(specification);
    }
}

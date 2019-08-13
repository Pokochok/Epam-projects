package by.epam.tourAgency.logic;

import by.epam.tourAgency.entity.Role;
import by.epam.tourAgency.exception.LogicException;
import by.epam.tourAgency.exception.RepositoryException;
import by.epam.tourAgency.repository.Repository;
import by.epam.tourAgency.repository.impl.TourRepository;
import by.epam.tourAgency.repository.impl.UserRepository;
import by.epam.tourAgency.specification.*;
import by.epam.tourAgency.specification.impl.admin.FindAdminByEmailSpecification;
import by.epam.tourAgency.specification.impl.admin.FindAdminByLoginSpecification;
import by.epam.tourAgency.specification.impl.admin.FindAdminByPhoneNumberSpecification;
import by.epam.tourAgency.specification.impl.agent.FindAgentByEmailSpecification;
import by.epam.tourAgency.specification.impl.agent.FindAgentByLoginSpecification;
import by.epam.tourAgency.specification.impl.agent.FindAgentByPhoneNumberSpecification;
import by.epam.tourAgency.specification.impl.client.FindClientByEmailSpecification;
import by.epam.tourAgency.specification.impl.client.FindClientByLoginSpecification;
import by.epam.tourAgency.specification.impl.client.FindClientByPhoneNumberSpecification;
import by.epam.tourAgency.specification.impl.tour.FindTourByNameSpecification;

public class MatchOfUniqueFieldsDetector {
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

    public static boolean isExistsTourName(String tourName) throws LogicException {
        Specification specification = new FindTourByNameSpecification(tourName);
        try {
            return TourRepository.getInstance().isExistsQuery(specification);
        } catch (RepositoryException e) {
            throw new LogicException(e);
        }
    }
}

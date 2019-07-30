package by.epam.dbworker.manager;

import by.epam.dbworker.entity.Role;
import by.epam.dbworker.repository.Repository;
import by.epam.dbworker.repository.impl.UserRepository;
import by.epam.dbworker.specification.*;
import by.epam.dbworker.specification.impl.adminspecification.FindAdminByEmailSpecification;
import by.epam.dbworker.specification.impl.adminspecification.FindAdminByLoginSpecification;
import by.epam.dbworker.specification.impl.adminspecification.FindAdminByPhoneNumberSpecification;
import by.epam.dbworker.specification.impl.agentspecification.FindAgentByEmailSpecification;
import by.epam.dbworker.specification.impl.agentspecification.FindAgentByLoginSpecification;
import by.epam.dbworker.specification.impl.agentspecification.FindAgentByPhoneNumberSpecification;
import by.epam.dbworker.specification.impl.clientspecification.FindClientByEmailSpecification;
import by.epam.dbworker.specification.impl.clientspecification.FindClientByLoginSpecification;
import by.epam.dbworker.specification.impl.clientspecification.FindClientByPhoneNumberSpecification;

public class MatchOfUniqueFieldsDetector {
    public static boolean isExistsEmail(String email, Role role) {
        boolean flag = false;
        Repository repository = UserRepository.getInstance();
        Specification clientSpecification = new FindClientByEmailSpecification(email);
        Specification agentSpecification = new FindAgentByEmailSpecification(email);
        Specification adminSpecification = new FindAdminByEmailSpecification(email);
        flag = ((UserRepository) repository).isExistsQuery(clientSpecification)
                || ((UserRepository) repository).isExistsQuery(agentSpecification)
                || ((UserRepository) repository).isExistsQuery(adminSpecification);
        return flag;
    }

    public static boolean isExistsPhoneNumber(String phoneNumber, Role role) {
        boolean flag = false;
        Repository repository = UserRepository.getInstance();
        Specification clientSpecification = new FindClientByPhoneNumberSpecification(phoneNumber);
        Specification agentSpecification = new FindAgentByPhoneNumberSpecification(phoneNumber);
        Specification adminSpecification = new FindAdminByPhoneNumberSpecification(phoneNumber);
        flag = ((UserRepository) repository).isExistsQuery(clientSpecification)
                || ((UserRepository) repository).isExistsQuery(agentSpecification)
                || ((UserRepository) repository).isExistsQuery(adminSpecification);
        return flag;
    }

    public static boolean isExistsLogin(String login, Role role) {
        boolean flag = false;
        Repository repository = UserRepository.getInstance();
        Specification clientSpecification = new FindClientByLoginSpecification(login);
        Specification agentSpecification = new FindAgentByLoginSpecification(login);
        Specification adminSpecification = new FindAdminByLoginSpecification(login);
        flag = ((UserRepository) repository).isExistsQuery(clientSpecification)
                || ((UserRepository) repository).isExistsQuery(agentSpecification)
                || ((UserRepository) repository).isExistsQuery(adminSpecification);
        return flag;
    }
}

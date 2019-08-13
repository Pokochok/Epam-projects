package by.epam.tourAgency;

import by.epam.tourAgency.connectionpool.ProxyConnectionPool;
import by.epam.tourAgency.exception.LogicException;
import by.epam.tourAgency.exception.RepositoryException;
import by.epam.tourAgency.logic.MatchOfUniqueFieldsDetector;
import by.epam.tourAgency.repository.impl.UserRepository;
import by.epam.tourAgency.specification.Specification;
import by.epam.tourAgency.specification.impl.admin.FindAdminByLoginPasswordSpecification;
import by.epam.tourAgency.specification.impl.agent.FindAgentByIdSpecification;
import by.epam.tourAgency.specification.impl.agent.FindAgentByLoginPasswordSpecification;
import by.epam.tourAgency.specification.impl.ticket.FindTicketByIdSpecification;
import by.epam.tourAgency.specification.impl.tour.FindTourByIdSpecification;
import by.epam.tourAgency.util.SHAEncrypting;

public class DBWorker {

    public static void main(String[] args) throws LogicException, RepositoryException {
        Specification tourQuery = new FindTourByIdSpecification(Integer.parseInt("16"));
        Specification ticketQuery = new FindTicketByIdSpecification(Integer.parseInt("2"));
        Specification agentQuery = new FindAgentByIdSpecification(Integer.parseInt("1"));

        UserRepository repository = UserRepository.getInstance();
        System.out.println(repository.isExistsQuery(new FindAgentByLoginPasswordSpecification("not defined",
                SHAEncrypting.hidePassword("not defined"))));
        ProxyConnectionPool.getInstance().closePool();
    }
}

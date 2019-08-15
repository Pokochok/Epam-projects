package by.epam.tourAgency.logic;

import by.epam.tourAgency.entity.Order;
import by.epam.tourAgency.entity.Ticket;
import by.epam.tourAgency.entity.Tour;
import by.epam.tourAgency.entity.User;
import by.epam.tourAgency.exception.LogicException;
import by.epam.tourAgency.exception.RepositoryException;
import by.epam.tourAgency.repository.Repository;
import by.epam.tourAgency.repository.impl.OrderRepository;
import by.epam.tourAgency.repository.impl.TicketRepository;
import by.epam.tourAgency.repository.impl.TourRepository;
import by.epam.tourAgency.repository.impl.UserRepository;
import by.epam.tourAgency.specification.Specification;
import by.epam.tourAgency.specification.impl.agent.FindAgentByIdSpecification;
import by.epam.tourAgency.specification.impl.client.FindClientByEmailSpecification;
import by.epam.tourAgency.specification.impl.client.FindClientByIdSpecification;
import by.epam.tourAgency.specification.impl.order.AddOrderSpecification;
import by.epam.tourAgency.specification.impl.ticket.FindTicketByIdSpecification;
import by.epam.tourAgency.specification.impl.tour.FindTourByIdSpecification;
import by.epam.tourAgency.util.Validation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Set;

/**
 * Contains business logic of booking logic
 */
public class BookingLogic {

    /**
     * Check if client with such email exists
     * @param clientEmail for checking
     * @return true if client exists, false - if not
     * @throws LogicException if handled RepositoryException
     */
    public static boolean isClientExists(String clientEmail) throws LogicException {
        Specification clientByEmail = new FindClientByEmailSpecification(clientEmail);
        try {
            return UserRepository.getInstance().isExistsQuery(clientByEmail);
        } catch (RepositoryException e) {
            throw new LogicException(e);
        }
    }

    /**
     * Defines, what specification will be used in client query
     * @param clientId clients ID
     * @param clientEmail clients email
     * @param agentId agents ID
     * @return specification
     */
    private static Specification defineClientSpecification(String clientId, String clientEmail, String agentId){
        Specification clientQuery = null;
        if (agentId.equals("0")) {
            clientQuery = new FindClientByIdSpecification(Integer.parseInt(clientId));
        } else {
            clientQuery = new FindClientByEmailSpecification(clientEmail);
        }
        return clientQuery;
    }

    /**
     * Processes parameters and create oreder
     * @param tourId tour ID
     * @param ticketId ticket ID
     * @param clientId client ID
     * @param clientEmail client email
     * @param agentId agent ID
     * @return order if all parameters are valid, and
     * returns null - if not
     * @throws LogicException if handled RepositoryException
     */
    private static Order orderProcessing(String tourId, String ticketId, String clientId, String clientEmail, String agentId) throws LogicException {
        agentId = Validation.validateId(agentId) ? agentId : "0";
        tourId = Validation.validateId(tourId) ? tourId : "0";
        ticketId = Validation.validateId(ticketId) ? ticketId : "0";

        Specification clientQuery = defineClientSpecification(clientId, clientEmail, agentId);
        Specification tourQuery = new FindTourByIdSpecification(Integer.parseInt(tourId));
        Specification ticketQuery = new FindTicketByIdSpecification(Integer.parseInt(ticketId));
        Specification agentQuery = new FindAgentByIdSpecification(Integer.parseInt(agentId));

        Set<Tour> tours = null;
        Set<Ticket> tickets = null;
        Set<User> clients = null;
        Set<User> agents = null;

        try {
            tickets = TicketRepository.getInstance().query(ticketQuery);
            clients = UserRepository.getInstance().query(clientQuery);
            agents = UserRepository.getInstance().query(agentQuery);
            tours = TourRepository.getInstance().query(tourQuery);
        } catch (RepositoryException e) {
            throw new LogicException(e);
        }

        Order order = null;
        if (!(clients.isEmpty() || agents.isEmpty())) {
            order = new Order.OrderBuilder()
            .setTour(tours.iterator().next())
            .setTicket(tickets.iterator().next())
            .setClient(clients.iterator().next())
            .setAgent(agents.iterator().next())
            .setPaymentState(false).build();
        }
        return order;
    }

    /**
     * Adds order to database
     * @param tourId tour ID
     * @param ticketId ticket ID
     * @param clientId client ID
     * @param clientEmail client email
     * @param agentId agent ID
     * @return true, if order was added, and false - if not
     * @throws LogicException if handled RepositoryException
     */
    public static boolean addOrder(String tourId, String ticketId, String clientId, String clientEmail, String agentId)
            throws LogicException {
        boolean flag = false;
        Order order = BookingLogic.orderProcessing(tourId, ticketId, clientId, clientEmail, agentId);
        try {
            if (order != null) {
                flag = true;
                Specification specification = new AddOrderSpecification(order);
                Repository repository = OrderRepository.getInstance();
                repository.add(order, specification);
            }
        } catch (RepositoryException e) {
            throw new LogicException(e);
        }
        return flag;
    }
}

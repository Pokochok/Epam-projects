package by.epam.touragency.logic;

import by.epam.touragency.entity.Order;
import by.epam.touragency.entity.Ticket;
import by.epam.touragency.entity.Tour;
import by.epam.touragency.entity.User;
import by.epam.touragency.repository.Repository;
import by.epam.touragency.specification.Specification;
import by.epam.touragency.specification.impl.agent.FindAgentByIdSpecification;
import by.epam.touragency.specification.impl.client.FindClientByEmailSpecification;
import by.epam.touragency.specification.impl.client.FindClientByIdSpecification;
import by.epam.touragency.specification.impl.order.AddOrderSpecification;
import by.epam.touragency.specification.impl.ticket.FindTicketByIdSpecification;
import by.epam.touragency.specification.impl.tour.FindTourByIdSpecification;
import by.epam.touragency.util.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Contains business logic of booking logic
 */
@Service
public class BookingLogic {
    @Autowired
    @Qualifier("orderRepository")
    private Repository<Order> orderRepository;

    @Autowired
    @Qualifier("ticketRepository")
    private Repository<Ticket> ticketRepository;

    @Autowired
    @Qualifier("userRepository")
    private Repository<User> userRepository;

    @Autowired
    @Qualifier("tourRepository")
    private Repository<Tour> tourRepository;

    @Autowired
    private Validation validation;

    /**
     * Check if client with such email exists
     *
     * @param clientEmail for checking
     * @return true if client exists, false - if not
     */
    public boolean isClientExists(String clientEmail) {
        if (!validation.validateEmail(clientEmail)) {
            return false;
        }
        Specification clientByEmail = new FindClientByEmailSpecification(clientEmail);
        return userRepository.isExistsQuery(clientByEmail);
    }


    /**
     * Defines, what specification will be used in client query
     *
     * @param clientId    clients ID
     * @param clientEmail clients email
     * @param agentId     agents ID
     * @return specification
     */
    private Specification defineClientSpecification(String clientId, String clientEmail, String agentId) {
        Specification clientQuery = null;
        if (agentId.equals("0")) {
            clientQuery = new FindClientByIdSpecification(Integer.parseInt(clientId)); // FIXME: 11/15/2019 FindUserByIdSpecification
        } else {
            clientQuery = new FindClientByEmailSpecification(clientEmail);
        }
        return clientQuery;
    }

    /**
     * Processes parameters and create oreder
     *
     * @param tourId      tour ID
     * @param ticketId    ticket ID
     * @param clientId    client ID
     * @param clientEmail client email
     * @param agentId     agent ID
     * @return order if all parameters are valid, and
     * returns null - if not
     */
    private Order orderProcessing(String tourId, String ticketId, String clientId, String clientEmail, String agentId) {
        agentId = validation.validateId(agentId) ? agentId : "0";
        tourId = validation.validateId(tourId) ? tourId : "0";
        ticketId = validation.validateId(ticketId) ? ticketId : "0";

        Specification clientQuery = defineClientSpecification(clientId, clientEmail, agentId);
        Specification tourQuery = new FindTourByIdSpecification(Integer.parseInt(tourId));
        Specification ticketQuery = new FindTicketByIdSpecification(Integer.parseInt(ticketId));
        Specification agentQuery = new FindAgentByIdSpecification(Integer.parseInt(agentId));

        Set<Tour> tours = null;
        Set<Ticket> tickets = null;
        Set<User> clients = null;
        Set<User> agents = null;

        tickets = ticketRepository.query(ticketQuery);
        clients = userRepository.query(clientQuery);
        agents = userRepository.query(agentQuery);
        tours = tourRepository.query(tourQuery);

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
     *
     * @param tourId      tour ID
     * @param ticketId    ticket ID
     * @param clientId    client ID
     * @param clientEmail client email
     * @param agentId     agent ID
     * @return true, if order was added, and false - if not
     */
    public boolean addOrder(String tourId, String ticketId, String clientId, String clientEmail, String agentId) {
        boolean flag = false;
        Order order = orderProcessing(tourId, ticketId, clientId, clientEmail, agentId);
        if (order != null) {
            flag = true;
            Specification specification = new AddOrderSpecification(order);
            orderRepository.add(order, specification);
        }
        return flag;
    }
}

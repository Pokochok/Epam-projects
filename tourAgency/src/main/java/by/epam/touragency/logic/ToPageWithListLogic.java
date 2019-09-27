package by.epam.touragency.logic;

import by.epam.touragency.entity.Order;
import by.epam.touragency.entity.Role;
import by.epam.touragency.entity.Ticket;
import by.epam.touragency.entity.Tour;
import by.epam.touragency.exception.LogicException;
import by.epam.touragency.exception.RepositoryException;
import by.epam.touragency.repository.impl.OrderRepository;
import by.epam.touragency.repository.impl.TicketRepository;
import by.epam.touragency.repository.impl.TourRepository;
import by.epam.touragency.specification.Specification;
import by.epam.touragency.specification.impl.order.FindAllOrdersSpecification;
import by.epam.touragency.specification.impl.order.FindClientOrdersSpecification;
import by.epam.touragency.specification.impl.ticket.FindAllTicketsSpecification;
import by.epam.touragency.specification.impl.tour.FindAllToursSpecification;
import by.epam.touragency.specification.impl.tour.FindAvailableToursSpecification;

import java.util.Date;
import java.util.Set;

/**
 * For pages with element list logic
 */
public class ToPageWithListLogic {
    /**
     * Creates ticket set
     * @return ticket set
     * @throws LogicException if handled RepositoryException
     */
    public static Set<Ticket> getTicketSet() throws LogicException {
        Specification specification = new FindAllTicketsSpecification();
        TicketRepository repository = TicketRepository.getInstance();
        Set<Ticket> ticketSet = null;
        try {
            ticketSet = repository.query(specification);
        } catch (RepositoryException e) {
            throw new LogicException(e);
        }
        return ticketSet;
    }

    /**
     * Creates tour set
     * @param userRole user role
     * @return tour set
     * @throws LogicException if handled RepositoryException
     */
    public static Set<Tour> getTourSet(String userRole) throws LogicException {
        Specification specification = defineTourSpecification(userRole);

        TourRepository repository = TourRepository.getInstance();
        Set<Tour> tourSet = null;
        try {
            tourSet = repository.query(specification);
        } catch (RepositoryException e) {
            throw new LogicException(e);
        }
        return tourSet;
    }

    /**
     * Defines specification for tour query
     * @param userRole user Role
     * @return specification
     */
    private static Specification defineTourSpecification(String userRole){
        Specification specification = null;
        if (Role.ADMIN.equals(Role.valueOf(userRole))) {
            specification = new FindAllToursSpecification();
        }else{
            specification = new FindAvailableToursSpecification(new Date().getTime());
        }
        return specification;
    }

    /**
     * Creates order set
     * @param userRole user role
     * @param userId user ID
     * @return order set
     * @throws LogicException if handled RepositoryException
     */
    public static Set<Order> getOrderSet(String userRole, String userId) throws LogicException {
        Specification specification = defineOrderSpecification(userRole, userId);

        OrderRepository repository = OrderRepository.getInstance();
        Set<Order> orderSet = null;
        try {
            orderSet = repository.query(specification);
        } catch (RepositoryException e) {
            throw new LogicException(e);
        }
        return orderSet;
    }

    /**
     * Defines specification for order query
     * @param userRole user role
     * @param userId user ID
     * @return specification
     */
    private static Specification defineOrderSpecification(String userRole, String userId){
        Specification specification = null;
        if (Role.CLIENT.equals(Role.valueOf(userRole))) {
            specification = new FindClientOrdersSpecification(Integer.parseInt(userId));
        }else{
            specification = new FindAllOrdersSpecification();
        }
        return specification;
    }
}

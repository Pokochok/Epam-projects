package by.epam.touragency.logic;

import by.epam.touragency.entity.Order;
import by.epam.touragency.entity.Role;
import by.epam.touragency.entity.Ticket;
import by.epam.touragency.entity.Tour;
import by.epam.touragency.repository.Repository;
import by.epam.touragency.repository.impl.TicketRepository;
import by.epam.touragency.repository.impl.TourRepository;
import by.epam.touragency.specification.Specification;
import by.epam.touragency.specification.impl.order.FindAllOrdersSpecification;
import by.epam.touragency.specification.impl.order.FindClientOrdersSpecification;
import by.epam.touragency.specification.impl.ticket.FindAllTicketsSpecification;
import by.epam.touragency.specification.impl.tour.FindAllToursSpecification;
import by.epam.touragency.specification.impl.tour.FindAvailableToursSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Set;

/**
 * For pages with element list logic
 */
@Service
public class ToPageWithListLogic {
    @Autowired
    @Qualifier("orderRepository")
    private Repository<Order> orderRepository;

    /**
     * Creates ticket set
     *
     * @return ticket set
     */
    public Set<Ticket> getTicketSet() {
        Specification specification = new FindAllTicketsSpecification();
        TicketRepository repository = TicketRepository.getInstance();
        Set<Ticket> ticketSet = null;
        ticketSet = repository.query(specification);
        return ticketSet;
    }

    /**
     * Creates tour set
     *
     * @param userRole user role
     * @return tour set
     */
    public Set<Tour> getTourSet(String userRole) {
        Specification specification = defineTourSpecification(userRole);

        TourRepository repository = TourRepository.getInstance();
        Set<Tour> tourSet = null;
        tourSet = repository.query(specification);
        return tourSet;
    }

    /**
     * Defines specification for tour query
     *
     * @param userRole user Role
     * @return specification
     */
    private Specification defineTourSpecification(String userRole) {
        Specification specification = null;
        if (Role.ADMIN.equals(Role.valueOf(userRole))) {
            specification = new FindAllToursSpecification();
        } else {
            specification = new FindAvailableToursSpecification(new Date().getTime());
        }
        return specification;
    }

    /**
     * Creates order set
     *
     * @param userRole user role
     * @param userId   user ID
     * @return order set
     */
    public Set<Order> getOrderSet(String userRole, String userId) {
        Specification specification = defineOrderSpecification(userRole, userId);

        Set<Order> orderSet = null;
        orderSet = orderRepository.query(specification);
        return orderSet;
    }

    /**
     * Defines specification for order query
     *
     * @param userRole user role
     * @param userId   user ID
     * @return specification
     */
    private Specification defineOrderSpecification(String userRole, String userId) {
        Specification specification = null;
        if (Role.CLIENT.equals(Role.valueOf(userRole))) {
            specification = new FindClientOrdersSpecification(Integer.parseInt(userId));
        } else {
            specification = new FindAllOrdersSpecification();
        }
        return specification;
    }
}

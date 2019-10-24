package by.epam.touragency.logic;

import by.epam.touragency.entity.Order;
import by.epam.touragency.entity.Ticket;
import by.epam.touragency.exception.RepositoryException;
import by.epam.touragency.repository.Repository;
import by.epam.touragency.specification.Specification;
import by.epam.touragency.specification.impl.order.RemoveOrderByDepartureDateSpecification;
import by.epam.touragency.specification.impl.ticket.RemoveTicketsByDepartureDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class TicketService {
    @Autowired
    @Qualifier("orderRepository")
    private Repository<Order> orderRepository;

    @Autowired
    @Qualifier("ticketRepository")
    private Repository<Ticket> ticketRepository;

    @Transactional
    public void removeInvalidTicketsWithOrders() throws RepositoryException {
        long date = new Date().getTime();
        Specification orderSpecification = new RemoveOrderByDepartureDateSpecification(date);
        orderRepository.remove(null, orderSpecification);
        Specification ticketSpecification = new RemoveTicketsByDepartureDate(date);
        ticketRepository.remove(null, ticketSpecification);
    }
}

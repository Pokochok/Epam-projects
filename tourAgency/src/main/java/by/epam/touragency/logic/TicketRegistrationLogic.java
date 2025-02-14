package by.epam.touragency.logic;

import by.epam.touragency.entity.Ticket;
import by.epam.touragency.exception.LogicException;
import by.epam.touragency.exception.RepositoryException;
import by.epam.touragency.repository.impl.TicketRepository;
import by.epam.touragency.specification.Specification;
import by.epam.touragency.specification.impl.ticket.AddTicketSpecification;
import by.epam.touragency.specification.impl.ticket.FindTicketsByAllContentSpecification;
import org.springframework.stereotype.Service;

/**
 * For ticket registration logic
 */
@Service
public class TicketRegistrationLogic {
    /**
     * Checks, if ticket with such parameters exists
     * @param flightNumber flight number
     * @param ticketNumber ticket number
     * @param departureCity departure city
     * @param arrivalCity arrival city
     * @param departureDate departure date
     * @param arrivalDate arrival date
     * @return true, if ticket exists, and false - if not
     * @throws LogicException if handled RepositoryException
     */
    public boolean isTicketExists(String flightNumber, String ticketNumber, String departureCity,
                                         String arrivalCity, long departureDate, long arrivalDate) throws LogicException {
        boolean flag = false;
        Specification specificationForValidate = new FindTicketsByAllContentSpecification(Integer.parseInt(flightNumber),
                Integer.parseInt(ticketNumber), departureCity, arrivalCity, departureDate, arrivalDate);
        try {
            if (TicketRepository.getInstance().isExistsQuery(specificationForValidate)) {
                flag = true;
            }
        } catch (RepositoryException e) {
            throw new LogicException(e);
        }
        return flag;
    }

    /**
     * Adds ticket to database
     * @param flightNumber flight number
     * @param ticketNumber ticket number
     * @param departureCity departure city
     * @param arrivalCity arrival city
     * @param departureDate departure date
     * @param arrivalDate arrival date
     * @throws LogicException if handled RepositoryException
     */
    public void addTicket(String flightNumber, String ticketNumber, String departureCity,
                                 String arrivalCity, long departureDate, long arrivalDate) throws LogicException {
        Ticket ticket = new Ticket.TicketBuilder()
                .setFlightNumber(Integer.parseInt(flightNumber))
                .setTicketNumber(Integer.parseInt(ticketNumber))
                .setDepartureCity(departureCity)
                .setArrivalCity(arrivalCity)
                .setDepartureDateTime(departureDate)
                .setArrivalDateTime(arrivalDate).build();
        Specification specification = new AddTicketSpecification(ticket);
        try {
            TicketRepository.getInstance().add(ticket, specification);
        } catch (RepositoryException e) {
            throw new LogicException(e);
        }

    }
}

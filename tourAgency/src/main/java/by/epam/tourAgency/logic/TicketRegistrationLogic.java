package by.epam.tourAgency.logic;

import by.epam.tourAgency.entity.Ticket;
import by.epam.tourAgency.exception.LogicException;
import by.epam.tourAgency.exception.RepositoryException;
import by.epam.tourAgency.repository.impl.TicketRepository;
import by.epam.tourAgency.specification.Specification;
import by.epam.tourAgency.specification.impl.ticket.AddTicketSpecification;
import by.epam.tourAgency.specification.impl.ticket.FindTicketsByAllContentSpecification;

/**
 * For ticket registration logic
 */
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
    public static boolean isTicketExists(String flightNumber, String ticketNumber, String departureCity,
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
    public static void addTicket(String flightNumber, String ticketNumber, String departureCity,
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

package by.epam.touragency.logic;

import by.epam.touragency.entity.Ticket;
import by.epam.touragency.repository.Repository;
import by.epam.touragency.specification.Specification;
import by.epam.touragency.specification.impl.ticket.FindTicketsByAllContentSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * For ticket registration logic
 */
@Service
public class TicketRegistrationLogic {

    @Autowired
    @Qualifier("hibernateTicketRepository")
    private Repository<Ticket> ticketRepository;

    /**
     * Checks, if ticket with such parameters exists
     *
     * @param flightNumber  flight number
     * @param ticketNumber  ticket number
     * @param departureCity departure city
     * @param arrivalCity   arrival city
     * @param departureDate departure date
     * @param arrivalDate   arrival date
     * @return true, if ticket exists, and false - if not
     */
    public boolean isTicketExists(String flightNumber, String ticketNumber, String departureCity,
                                  String arrivalCity, long departureDate, long arrivalDate) {
        Specification specificationForValidate = new FindTicketsByAllContentSpecification(Integer.parseInt(flightNumber),
                Integer.parseInt(ticketNumber), departureCity, arrivalCity, departureDate, arrivalDate);
        return ticketRepository.isExistsQuery(specificationForValidate);
    }

    /**
     * Adds ticket to database
     *
     * @param flightNumber  flight number
     * @param ticketNumber  ticket number
     * @param departureCity departure city
     * @param arrivalCity   arrival city
     * @param departureDate departure date
     * @param arrivalDate   arrival date
     */
    public void addTicket(String flightNumber, String ticketNumber, String departureCity,
                          String arrivalCity, long departureDate, long arrivalDate) {
        Ticket ticket = new Ticket.TicketBuilder()
                .setFlightNumber(Integer.parseInt(flightNumber))
                .setTicketNumber(Integer.parseInt(ticketNumber))
                .setDepartureCity(departureCity)
                .setArrivalCity(arrivalCity)
                .setDepartureDateTime(departureDate)
                .setArrivalDateTime(arrivalDate).build();
        ticketRepository.add(ticket, null);
    }
}

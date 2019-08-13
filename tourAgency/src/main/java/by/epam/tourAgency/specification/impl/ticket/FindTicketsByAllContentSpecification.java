package by.epam.tourAgency.specification.impl.ticket;

import by.epam.tourAgency.entity.Ticket;
import by.epam.tourAgency.specification.Specification;

import java.util.ArrayDeque;

public class FindTicketsByAllContentSpecification implements Specification<Ticket> {
    private static final String FIND_SPECIFICATION_SQL_BY_CONTENT = "SELECT id, flight_number, ticket_number, " +
            "departure_city, arrival_city, departure_datetime, arrival_datetime FROM tickets WHERE flight_number=? AND " +
            "ticket_number=? AND departure_city=? AND arrival_city=? AND departure_datetime=? AND arrival_datetime=?;";
    private int flightNumber;
    private int ticketNumber;
    private String departureCity;
    private String arrivalCity;
    private long departureDateTime;
    private long arrivalDateTime;

    public FindTicketsByAllContentSpecification(int flightNumber, int ticketNumber, String departureCity, String arrivalCity,
                                                long departureDateTime, long arrivalDateTime) {
        this.flightNumber = flightNumber;
        this.ticketNumber = ticketNumber;
        this.departureCity = departureCity;
        this.arrivalCity = arrivalCity;
        this.departureDateTime = departureDateTime;
        this.arrivalDateTime = arrivalDateTime;
    }

    @Override
    public String sqlQuery() {
        return FIND_SPECIFICATION_SQL_BY_CONTENT;
    }

    @Override
    public ArrayDeque<Object> getParameterQueue() {
        ArrayDeque<Object> values = new ArrayDeque<>(1);
        values.push(flightNumber);
        values.push(ticketNumber);
        values.push(departureCity);
        values.push(arrivalCity);
        values.push(departureDateTime);
        values.push(arrivalDateTime);
        return values;
    }
}

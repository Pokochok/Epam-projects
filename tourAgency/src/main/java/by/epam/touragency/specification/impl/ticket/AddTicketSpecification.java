package by.epam.touragency.specification.impl.ticket;

import by.epam.touragency.entity.Ticket;
import by.epam.touragency.specification.Specification;

import java.util.ArrayDeque;

public class AddTicketSpecification implements Specification<Ticket> {
    private static final String ADD_ORDER_SQL_ROW = "INSERT INTO tickets(flight_number, ticket_number, departure_city, " +
            "arrival_city, departure_datetime, arrival_datetime) " +
            "VALUES (?, ?, ?, ?, ?, ?);";
    private Ticket ticket;

    public AddTicketSpecification(Ticket ticket) {
        this.ticket = ticket;
    }

    @Override
    public String sqlQuery() {
        return ADD_ORDER_SQL_ROW;
    }

    @Override
    public ArrayDeque<Object> getParameterQueue() {
        ArrayDeque<Object> values = new ArrayDeque<>(6);
        values.push(ticket.getFlightNumber());
        values.push(ticket.getTicketNumber());
        values.push(ticket.getDepartureCity());
        values.push(ticket.getArrivalCity());
        values.push(ticket.getDepartureDateTimeLong());
        values.push(ticket.getArrivalDateTimeLong());
        return values;
    }
}

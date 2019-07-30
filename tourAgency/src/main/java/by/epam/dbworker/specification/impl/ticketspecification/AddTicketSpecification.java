package by.epam.dbworker.specification.impl.ticketspecification;

import by.epam.dbworker.entity.Ticket;
import by.epam.dbworker.specification.Specification;

import java.util.ArrayDeque;

public class AddTicketSpecification implements Specification<Ticket> {
    private static final String ADD_ORDER_SQL_ROW = "INSERT INTO tickets(flight_number, ticket_number, departure_airport, arrival_airport, " +
            "departure_datetime, arrival_datetime) " +
            "VALUES (?, ?, ?, ?, ?, ?);";
    private Ticket ticket;

    public AddTicketSpecification(Ticket ticket) {
        this.ticket = ticket;
    }

    @Override
    public boolean specify(Ticket entity) {
        return false;
    }

    @Override
    public String sqlQuery() {
        return ADD_ORDER_SQL_ROW;
    }

    @Override
    public ArrayDeque getParameterQueue() {
        ArrayDeque values = new ArrayDeque(6);
        values.push(ticket.getFlightNumber());
        values.push(ticket.getTicketNumber());
        values.push(ticket.getDepartureAirport());
        values.push(ticket.getArrivalAirport());
        values.push(ticket.getDepartureDateTime());
        values.push(ticket.getArrivalDateTime());
        return values;
    }
}

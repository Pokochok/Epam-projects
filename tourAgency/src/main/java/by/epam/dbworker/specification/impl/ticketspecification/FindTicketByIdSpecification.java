package by.epam.dbworker.specification.impl.ticketspecification;

import by.epam.dbworker.entity.Ticket;
import by.epam.dbworker.specification.Specification;

import java.util.ArrayDeque;

public class FindTicketByIdSpecification implements Specification<Ticket> {
    private static final String FIND_SPECIFICATION_SQL_BY_ID = "SELECT id, flight_number, ticket_number, departure_airport, arrival_airport, " +
            "departure_datetime, arrival_datetime FROM tours WHERE id=?;";
    private int id;

    public FindTicketByIdSpecification(int id) {
        this.id = id;
    }

    @Override
    public boolean specify(Ticket entity) {
        return entity.getId() == id;
    }

    @Override
    public String sqlQuery() {
        return FIND_SPECIFICATION_SQL_BY_ID;
    }

    @Override
    public ArrayDeque getParameterQueue() {
        ArrayDeque values = new ArrayDeque(1);
        values.push(id);
        return values;
    }
}

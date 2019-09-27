package by.epam.touragency.specification.impl.ticket;

import by.epam.touragency.entity.Ticket;
import by.epam.touragency.specification.Specification;

import java.util.ArrayDeque;

public class FindTicketByIdSpecification implements Specification<Ticket> {
    private static final String FIND_SPECIFICATION_SQL_BY_ID = "SELECT id, flight_number, ticket_number, " +
            "departure_city, arrival_city, departure_datetime, arrival_datetime FROM tickets WHERE id=?;";
    private int id;

    public FindTicketByIdSpecification(int id) {
        this.id = id;
    }

    @Override
    public String sqlQuery() {
        return FIND_SPECIFICATION_SQL_BY_ID;
    }

    @Override
    public ArrayDeque<Object> getParameterQueue() {
        ArrayDeque<Object> values = new ArrayDeque<>(1);
        values.push(id);
        return values;
    }
}

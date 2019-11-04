package by.epam.touragency.specification.impl.ticket;

import by.epam.touragency.entity.Order;
import by.epam.touragency.specification.Specification;

import java.util.ArrayDeque;

public class RemoveTicketsByDepartureDate implements Specification<Order> {
    private static final String DELETE_TICKETS_SPECIFICATION_SQL_BY_DEPARTURE_DATE =
            "DELETE FROM tickets WHERE id != 0 AND arrival_datetime < ?;";
    private long date;

    public RemoveTicketsByDepartureDate(long date) {
        this.date = date;
    }

    @Override
    public String sqlQuery() {
        return DELETE_TICKETS_SPECIFICATION_SQL_BY_DEPARTURE_DATE;
    }

    @Override
    public ArrayDeque<Object> getParameterQueue() {
        ArrayDeque<Object> values = new ArrayDeque<>(1);
        values.add(date);
        return values;
    }
}

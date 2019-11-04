package by.epam.touragency.specification.impl.order;

import by.epam.touragency.entity.Order;
import by.epam.touragency.specification.Specification;

import java.util.ArrayDeque;

public class RemoveOrderByDepartureDateSpecification implements Specification<Order> {
    private static final String DELETE_ORDER_SPECIFICATION_SQL_BY_DEPARTURE_DATE =
            "DELETE FROM orders where orders.id_ticket IN (SELECT tickets.id FROM tickets where arrival_datetime < ?);";
    private long date;

    public RemoveOrderByDepartureDateSpecification(long date) {
        this.date = date;
    }

    @Override
    public String sqlQuery() {
        return DELETE_ORDER_SPECIFICATION_SQL_BY_DEPARTURE_DATE;
    }

    @Override
    public ArrayDeque<Object> getParameterQueue() {
        ArrayDeque<Object> values = new ArrayDeque<>(1);
        values.add(date);
        return values;
    }
}

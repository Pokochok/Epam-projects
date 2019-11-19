package by.epam.touragency.specification.impl.order;

import by.epam.touragency.entity.Order;
import by.epam.touragency.specification.Specification;

import java.util.ArrayDeque;

public class FindOrderByTicketIdSpecification implements Specification<Order> {
    private static final String FIND_ALL_SPECIFICATION_SQL = "SELECT id, payment_state, id_tour, id_ticket, id_client, " +
            "id_agent FROM orders WHERE id_ticket = ?;";
    private int ticketId;

    public FindOrderByTicketIdSpecification(int ticketId) {
        this.ticketId = ticketId;
    }

    @Override
    public String sqlQuery() {
        return FIND_ALL_SPECIFICATION_SQL;
    }

    @Override
    public ArrayDeque<Object> getParameterQueue() {
        ArrayDeque<Object> values = new ArrayDeque<>(1);
        values.add(ticketId);
        return values;
    }
}

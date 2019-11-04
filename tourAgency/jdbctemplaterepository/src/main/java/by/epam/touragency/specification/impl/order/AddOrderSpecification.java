package by.epam.touragency.specification.impl.order;

import by.epam.touragency.entity.Order;
import by.epam.touragency.specification.Specification;

import java.util.ArrayDeque;

public class AddOrderSpecification implements Specification<Order> {
    private static final String ADD_ORDER_SQL_ROW = "INSERT INTO orders(payment_state, id_tour, id_ticket, id_client, id_agent) " +
            "VALUES (?, ?, ?, ?, ?);";
    private Order order;

    public AddOrderSpecification(Order order) {
        this.order = order;
    }

    @Override
    public String sqlQuery() {
        return ADD_ORDER_SQL_ROW;
    }

    @Override
    public ArrayDeque<Object> getParameterQueue() {
        ArrayDeque<Object> values = new ArrayDeque<>(5);
        values.add(Boolean.toString(order.getPaymentState()));
        values.add(order.getTour().getId());
        values.add(order.getTicket().getId());
        values.add(order.getClient().getId());
        values.add(order.getAgent().getId());
        return values;
    }
}

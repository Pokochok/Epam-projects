package by.epam.tourAgency.specification.impl.order;

import by.epam.tourAgency.entity.Order;
import by.epam.tourAgency.specification.Specification;

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
        values.push(Boolean.toString(order.getPaymentState()));
        values.push(order.getTour().getId());
        values.push(order.getTicket().getId());
        values.push(order.getClient().getId());
        values.push(order.getAgent().getId());
        return values;
    }
}

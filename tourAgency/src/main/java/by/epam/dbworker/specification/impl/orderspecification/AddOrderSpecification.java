package by.epam.dbworker.specification.impl.orderspecification;

import by.epam.dbworker.entity.Order;
import by.epam.dbworker.specification.Specification;

import java.util.ArrayDeque;

public class AddOrderSpecification implements Specification<Order> {
    private static final String ADD_ORDER_SQL_ROW = "INSERT INTO orders(payment_state, id_tour, id_ticket, id_client) " +
            "VALUES (?, ?, ?, ?);";
    private Order order;

    public AddOrderSpecification(Order order) {
        this.order = order;
    }

    @Override
    public boolean specify(Order entity) {
        return false;
    }

    @Override
    public String sqlQuery() {
        return ADD_ORDER_SQL_ROW;
    }

    @Override
    public ArrayDeque getParameterQueue() {
        ArrayDeque values = new ArrayDeque(4);
        values.push(Boolean.toString(order.isPaymentState()));
        values.push(order.getTour().getId());
        values.push(order.getTicket().getId());
        values.push(order.getUser().getId());
        return values;
    }
}

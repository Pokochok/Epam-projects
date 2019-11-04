package by.epam.touragency.specification.impl.order;

import by.epam.touragency.entity.Order;
import by.epam.touragency.specification.Specification;

import java.util.ArrayDeque;

public class FindAllOrdersSpecification implements Specification<Order> {
    private static final String FIND_ALL_SPECIFICATION_SQL = "SELECT id, payment_state, id_tour, id_ticket, id_client, " +
            "id_agent FROM orders;";

    public FindAllOrdersSpecification() {
    }

    @Override
    public String sqlQuery() {
        return FIND_ALL_SPECIFICATION_SQL;
    }

    @Override
    public ArrayDeque<Object> getParameterQueue() {
        return new ArrayDeque<>(0);
    }
}


package by.epam.tourAgency.specification.impl.order;

import by.epam.tourAgency.entity.Order;
import by.epam.tourAgency.specification.Specification;

import java.util.ArrayDeque;

public class RemoveOrderByIdSpecification implements Specification<Order> {
    private static final String DELETE_ORDER_SPECIFICATION_SQL_BY_ID =
            "DELETE FROM orders WHERE id=?;";
    private int id;

    public RemoveOrderByIdSpecification(int id) {
        this.id = id;
    }

    @Override
    public String sqlQuery() {
        return DELETE_ORDER_SPECIFICATION_SQL_BY_ID;
    }

    @Override
    public ArrayDeque<Object> getParameterQueue() {
        ArrayDeque<Object> values = new ArrayDeque<>(1);
        values.push(id);
        return values;
    }
}

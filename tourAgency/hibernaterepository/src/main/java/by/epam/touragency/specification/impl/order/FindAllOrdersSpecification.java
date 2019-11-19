package by.epam.touragency.specification.impl.order;

import by.epam.touragency.entity.Order;
import by.epam.touragency.specification.Specification;

import java.util.ArrayDeque;

public class FindAllOrdersSpecification implements Specification<Order> {
    private static final String FIND_ALL_SPECIFICATION_SQL = "FROM Order";

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


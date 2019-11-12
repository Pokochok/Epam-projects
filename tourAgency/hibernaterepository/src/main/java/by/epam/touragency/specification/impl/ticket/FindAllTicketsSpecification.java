package by.epam.touragency.specification.impl.ticket;

import by.epam.touragency.entity.Ticket;
import by.epam.touragency.specification.Specification;

import java.util.ArrayDeque;

public class FindAllTicketsSpecification implements Specification<Ticket> {
    private static final String FIND_ALL_SPECIFICATION_SQL = "FROM Ticket WHERE id!=0";

    @Override
    public String sqlQuery() {
        return FIND_ALL_SPECIFICATION_SQL;
    }

    @Override
    public ArrayDeque<Object> getParameterQueue() {
        return new ArrayDeque<>(0);
    }
}
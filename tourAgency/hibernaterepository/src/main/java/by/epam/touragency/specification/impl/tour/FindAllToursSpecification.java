package by.epam.touragency.specification.impl.tour;

import by.epam.touragency.entity.Tour;
import by.epam.touragency.specification.Specification;

import java.util.ArrayDeque;

public class FindAllToursSpecification implements Specification<Tour> {
    private static final String FIND_SPECIFICATION_SQL = "FROM Tour WHERE status='AVAILABLE' OR status='NOT_AVAILABLE'";

    @Override
    public String sqlQuery() {
        return FIND_SPECIFICATION_SQL;
    }

    @Override
    public ArrayDeque<Object> getParameterQueue() {
        return new ArrayDeque<>(0);
    }
}

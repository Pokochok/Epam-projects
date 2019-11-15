package by.epam.touragency.specification.impl.tour;

import by.epam.touragency.entity.Tour;
import by.epam.touragency.specification.Specification;

import java.util.ArrayDeque;

public class FindAvailableToursSpecification implements Specification<Tour> {
    private static final String FIND_AVAILABLE_SPECIFICATION_SQL = "FROM Tour WHERE status='AVAILABLE' AND departureDate>?1";
    private long departureDate;

    public FindAvailableToursSpecification(long departureDate) {
        this.departureDate = departureDate;
    }

    @Override
    public String sqlQuery() {
        return FIND_AVAILABLE_SPECIFICATION_SQL;
    }

    @Override
    public ArrayDeque<Object> getParameterQueue() {
        ArrayDeque<Object> values = new ArrayDeque<>(1);
        values.add(departureDate);
        return values;
    }
}
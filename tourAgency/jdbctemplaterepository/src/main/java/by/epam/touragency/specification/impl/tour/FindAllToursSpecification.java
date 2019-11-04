package by.epam.touragency.specification.impl.tour;

import by.epam.touragency.entity.Tour;
import by.epam.touragency.specification.Specification;

import java.util.ArrayDeque;

public class FindAllToursSpecification implements Specification<Tour> {
    private static final String FIND_SPECIFICATION_SQL = "SELECT id, tour_name, departure_date, arrival_date, departure_city, " +
            "arrival_city, arrival_country, hotel, nutrition, adults_number, children_number, price, status FROM tours " +
            "WHERE status='AVAILABLE' OR status='NOT_AVAILABLE';";

    public FindAllToursSpecification() {
    }

    @Override
    public String sqlQuery() {
        return FIND_SPECIFICATION_SQL;
    }

    @Override
    public ArrayDeque<Object> getParameterQueue() {
        return new ArrayDeque<>(0);
    }
}

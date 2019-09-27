package by.epam.touragency.specification.impl.tour;

import by.epam.touragency.entity.Tour;
import by.epam.touragency.specification.Specification;

import java.util.ArrayDeque;

public class FindAvailableToursSpecification implements Specification<Tour> {
    private static final String FIND_AVAILABLE_SPECIFICATION_SQL = "SELECT id, tour_name, departure_date, arrival_date, departure_city, " +
            "arrival_city, arrival_country, hotel, nutrition, adults_number, children_number, price, status FROM tours " +
            "WHERE status='AVAILABLE' AND departure_date>?;";
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
        values.push(departureDate);
        return values;
    }
}

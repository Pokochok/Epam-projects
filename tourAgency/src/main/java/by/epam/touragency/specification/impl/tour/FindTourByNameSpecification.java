package by.epam.touragency.specification.impl.tour;

import by.epam.touragency.entity.Tour;
import by.epam.touragency.specification.Specification;

import java.util.ArrayDeque;

public class FindTourByNameSpecification implements Specification<Tour> {
    private static final String FIND_SPECIFICATION_SQL_BY_ID = "SELECT id, tour_name, departure_date, arrival_date, departure_city, " +
            "arrival_city, arrival_country, hotel, nutrition, adults_number, children_number, price, status FROM tours WHERE tour_name=?;";
    private String tourName;

    public FindTourByNameSpecification(String tourName) {
        this.tourName = tourName;
    }

    @Override
    public String sqlQuery() {
        return FIND_SPECIFICATION_SQL_BY_ID;
    }

    @Override
    public ArrayDeque<Object> getParameterQueue() {
        ArrayDeque<Object> values = new ArrayDeque<>(1);
        values.push(tourName);
        return values;
    }
}

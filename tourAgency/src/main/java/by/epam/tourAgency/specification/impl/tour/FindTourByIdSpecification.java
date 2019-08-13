package by.epam.tourAgency.specification.impl.tour;

import by.epam.tourAgency.entity.Tour;
import by.epam.tourAgency.specification.Specification;

import java.util.ArrayDeque;

public class FindTourByIdSpecification implements Specification<Tour> {
    private static final String FIND_SPECIFICATION_SQL_BY_ID = "SELECT id, tour_name, departure_date, arrival_date, departure_city, " +
            "arrival_city, arrival_country, hotel, nutrition, adults_number, children_number, price, status FROM tours WHERE id=?;";
    private int id;

    public FindTourByIdSpecification(int id) {
        this.id = id;
    }

    @Override
    public String sqlQuery() {
        return FIND_SPECIFICATION_SQL_BY_ID;
    }

    @Override
    public ArrayDeque<Object> getParameterQueue() {
        ArrayDeque<Object> values = new ArrayDeque<>(1);
        values.push(id);
        return values;
    }
}

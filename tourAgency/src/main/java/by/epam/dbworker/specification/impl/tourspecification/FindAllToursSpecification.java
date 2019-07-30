package by.epam.dbworker.specification.impl.tourspecification;

import by.epam.dbworker.entity.Tour;
import by.epam.dbworker.specification.Specification;

import java.util.ArrayDeque;

public class FindAllToursSpecification implements Specification<Tour> {
    private static final String FIND_SPECIFICATION_SQL_BY_NAME_SURNAME = "SELECT id, tour_name, departure_date, arrival_date, departure_city, " +
            "arrival_city, arrival_country, hotel, nutrition, adults_number, children_number, price, status FROM tours;";
    private ArrayDeque values;

    public FindAllToursSpecification() {
        values = new ArrayDeque(0);
    }

    @Override
    public boolean specify(Tour entity) {
        return true;
    }

    @Override
    public String sqlQuery() {
        return FIND_SPECIFICATION_SQL_BY_NAME_SURNAME;
    }

    @Override
    public ArrayDeque getParameterQueue() {
        return values;
    }
}

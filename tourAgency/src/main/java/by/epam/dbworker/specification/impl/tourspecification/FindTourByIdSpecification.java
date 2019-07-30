package by.epam.dbworker.specification.impl.tourspecification;

import by.epam.dbworker.entity.Tour;
import by.epam.dbworker.specification.Specification;

import java.util.ArrayDeque;

public class FindTourByIdSpecification implements Specification<Tour> {
    private static final String FIND_SPECIFICATION_SQL_BY_ID = "SELECT id, tour_name, departure_date, arrival_date, departure_city, " +
            "arrival_city, arrival_country, hotel, nutrition, adults_number, children_number, price FROM tours WHERE id=?;";
    private int id;

    public FindTourByIdSpecification(int id) {
        this.id = id;
    }

    @Override
    public boolean specify(Tour entity) {
        return entity.getId() == id;
    }

    @Override
    public String sqlQuery() {
        return FIND_SPECIFICATION_SQL_BY_ID;
    }

    @Override
    public ArrayDeque getParameterQueue() {
        ArrayDeque values = new ArrayDeque(1);
        values.push(id);
        return values;
    }
}
